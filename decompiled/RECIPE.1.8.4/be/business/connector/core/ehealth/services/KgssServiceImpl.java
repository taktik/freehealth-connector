package be.business.connector.core.ehealth.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PersistentCache;
import be.business.connector.core.utils.PersistentCacheType;
import be.business.connector.core.utils.PropertyHandler;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.ehealth.technicalconnector.service.ServiceFactory;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponseContent;
import com.sun.xml.ws.client.ClientTransportException;
import java.security.Key;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class KgssServiceImpl implements KgssService {
   private static final Logger LOG = Logger.getLogger(KgssServiceImpl.class);
   private static KgssService kgssService;
   private final PersistentCache<String, byte[]> kgssKeyCache;

   private KgssServiceImpl() throws IntegrationModuleException {
      this.kgssKeyCache = new PersistentCache(PropertyHandler.getInstance(), PersistentCacheType.kgss);
   }

   public static KgssService getInstance() throws IntegrationModuleException {
      if (kgssService == null) {
         kgssService = new KgssServiceImpl();
      }

      return kgssService;
   }

   public KeyResult retrieveKeyFromKgss(byte[] keyId, byte[] myEtk, byte[] kgssEtk) throws IntegrationModuleException {
      LOG.debug("KeyIdentifier : " + Arrays.toString(keyId));
      if (this.kgssKeyCache.containsKey(PersistentCache.rewriteKey(new String(keyId)))) {
         LOG.debug("KGSSKeyCache item found");
         byte[] key = (byte[])this.kgssKeyCache.get(PersistentCache.rewriteKey(new String(keyId)));
         return new KeyResult(new SecretKeySpec(key, "AES"), new String(keyId));
      } else {
         GetKeyRequestContent getKeyRequestContent = new GetKeyRequestContent();
         Key key = null;
         if (myEtk != null) {
            getKeyRequestContent.setETK(myEtk);
         } else {
            try {
               KeyGenerator keyGen = KeyGenerator.getInstance("AES");
               synchronized(keyGen) {
                  key = keyGen.generateKey();
               }
            } catch (Exception var11) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.technical"), var11);
            }

            getKeyRequestContent.setKeyEncryptionKey(key.getEncoded());
         }

         getKeyRequestContent.setKeyIdentifier(Base64.decodeBase64(keyId));

         try {
            be.ehealth.technicalconnector.service.kgss.KgssService kgss = ServiceFactory.getKgssService();
            SessionItem sessionItem = Session.getInstance().getSession();
            GetKeyResponseContent getKeyResponseContent = kgss.getKey(getKeyRequestContent, sessionItem.getHolderOfKeyCredential(), sessionItem.getEncryptionCredential(), sessionItem.getSAMLToken().getAssertion(), sessionItem.getEncryptionPrivateKeys(), kgssEtk);
            KeyResult keyResultToReturn = new KeyResult(new SecretKeySpec(getKeyResponseContent.getKey(), "AES"), new String(keyId));
            return keyResultToReturn;
         } catch (SOAPFaultException var12) {
            if (var12.getFault() != null && var12.getFault().getFaultCode() != null && var12.getFault().getFaultCode().contains("InvalidSecurity")) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.kgss.getKey"), var12);
            } else {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.kgss.getKey.other"), var12);
            }
         } catch (ClientTransportException var13) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.kgss"), var13);
         } catch (TechnicalConnectorException var14) {
            LOG.error("Error retrieving key", var14);
            throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.key"), var14);
         }
      }
   }

   public KeyResult retrieveNewKey(byte[] etkKgss, List<String> credentialTypes, String prescriberId, String executorId, String patientId, byte[] myEtk) throws IntegrationModuleException {
      GetNewKeyRequestContent req = new GetNewKeyRequestContent();
      req.setETK(myEtk);
      List<CredentialType> allowedReaders = req.getAllowedReaders();
      Iterator var10 = credentialTypes.iterator();

      while(var10.hasNext()) {
         String credentialTypeStr = (String)var10.next();
         String[] atrs = credentialTypeStr.split(",");
         if (atrs.length != 3 && atrs.length != 2) {
            throw new IntegrationModuleException("Invalid property : kgss.createPrescription.ACL.XXX = " + credentialTypeStr);
         }

         String value = "";
         if (atrs.length == 3) {
            value = atrs[2];
            value = value.replaceAll("%PRESCRIBER_ID%", prescriberId);
            value = value.replaceAll("%EXECUTOR_ID%", executorId);
            value = value.replaceAll("%PATIENT_ID%", patientId);
         }

         CredentialType ct = new CredentialType();
         ct.setNamespace(atrs[0]);
         ct.setName(atrs[1]);
         ct.getValues().add(value);
         allowedReaders.add(ct);
      }

      try {
         be.ehealth.technicalconnector.service.kgss.KgssService kgss = ServiceFactory.getKgssService();
         SessionItem sessionItem = Session.getInstance().getSession();
         GetNewKeyResponseContent getNewKeyResponseContent = kgss.getNewKey(req, sessionItem.getEncryptionCredential(), sessionItem.getEncryptionPrivateKeys(), etkKgss);
         byte[] keyResponse = getNewKeyResponseContent.getNewKey();
         byte[] keyId = getNewKeyResponseContent.getNewKeyIdentifier();
         KeyResult keyResultToReturn = new KeyResult(new SecretKeySpec(keyResponse, "AES"), new String(Base64.encodeBase64(keyId)));
         this.kgssKeyCache.put(PersistentCache.rewriteKey(keyResultToReturn.getKeyId()), keyResponse);
         return keyResultToReturn;
      } catch (TechnicalConnectorException var15) {
         LOG.error("Error retrieving new key", var15);
         if (var15 instanceof UnsealConnectorException && ((UnsealConnectorException)var15).getUnsealResult() != null) {
            List<NotificationError> decryptionFailure = ((UnsealConnectorException)var15).getUnsealResult().getErrors();
            Iterator var24 = decryptionFailure.iterator();

            while(var24.hasNext()) {
               NotificationError error = (NotificationError)var24.next();
               LOG.error("NotificationError: " + error.toString());
            }

            List<NotificationWarning> warnings = ((UnsealConnectorException)var15).getUnsealResult().getWarnings();
            Iterator var22 = warnings.iterator();

            while(var22.hasNext()) {
               NotificationWarning warning = (NotificationWarning)var22.next();
               LOG.error("NotificationWarning: " + warning.toString());
            }
         }

         throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.new.key"), var15);
      }
   }
}
