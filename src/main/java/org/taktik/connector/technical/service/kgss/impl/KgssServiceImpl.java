package org.taktik.connector.technical.service.kgss.impl;

import be.fgov.ehealth.etee.crypto.status.NotificationError;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.exception.UnsealConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.kgss.KgssService;
import org.taktik.connector.technical.service.kgss.builders.KgssMessageBuilder;
import org.taktik.connector.technical.service.kgss.builders.impl.KgssMessageBuilderImpl;
import org.taktik.connector.technical.service.kgss.domain.KeyResult;
import org.taktik.connector.technical.service.sts.SAMLTokenFactory;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential;
import org.taktik.connector.technical.service.ws.ServiceFactory;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.session.SessionItem;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import be.fgov.ehealth.etee.commons._1_0.etee.ErrorType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequest;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponse;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequest;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponse;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponseContent;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

import org.bouncycastle.util.encoders.Base64;
import org.w3c.dom.Element;

public class KgssServiceImpl implements KgssService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private Logger log = LoggerFactory.getLogger(this.getClass());

   public KeyResult getNewKey(GetNewKeyRequestContent request, byte[] kgssETK) throws TechnicalConnectorException {
      Credential encryptionCredential = Session.getInstance().getSession().getEncryptionCredential();
      Map<String, PrivateKey> decryptionKeys = Session.getInstance().getSession().getEncryptionPrivateKeys();
      GetNewKeyResponseContent response = this.getNewKey(request, encryptionCredential, decryptionKeys, kgssETK);
      byte[] keyResponse = response.getNewKey();
      String keyId = new String(Base64.encode(response.getNewKeyIdentifier()));
      return new KeyResult(new SecretKeySpec(keyResponse, "AES"), keyId);
   }

   public KeyResult getKey(GetKeyRequestContent request, byte[] kgssETK, SessionItem session) throws TechnicalConnectorException {
      Credential encryptionCredential = session.getEncryptionCredential();
      Map<String, PrivateKey> decryptionKeys = session.getEncryptionPrivateKeys();
      GetKeyResponseContent response = this.getKey(request, encryptionCredential, session.getSAMLToken(), session.getSAMLToken().getAssertion(), decryptionKeys, kgssETK);
      String keyId = new String(request.getKeyIdentifier());
      return new KeyResult(new SecretKeySpec(response.getKey(), "AES"), keyId);
   }

   public GetNewKeyResponseContent getNewKey(GetNewKeyRequestContent request, Credential encryption, Map<String, PrivateKey> decryptionKeys, byte[] etkKGSS) throws TechnicalConnectorException {
      KgssMessageBuilder builder = new KgssMessageBuilderImpl(etkKGSS, encryption, decryptionKeys);
      GetNewKeyRequest sealedRequest = builder.sealGetNewKeyRequest(request);
      GenericRequest genericRequest = ServiceFactory.getKGSSService();
      genericRequest.setPayload(sealedRequest);

      try {
         GetNewKeyResponse response = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(genericRequest).asObject(GetNewKeyResponse.class);
         checkReplyStatus(response.getStatus().getCode());
         this.checkErrorMessages(response.getErrors());
         return builder.unsealGetNewKeyResponse(response);
      } catch (SOAPException ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex.getMessage(), ex);
      }
   }

   private void checkErrorMessages(List<ErrorType> errors) throws TechnicalConnectorException {
      if (!errors.isEmpty()) {
         StringBuilder sb = new StringBuilder();
         sb.append("there were error messages in KGSS response : ");

         for (ErrorType errorType : errors) {
            sb.append("[code:").append(errorType.getCode()).append(" , messages:");

            for (LocalisedString message : errorType.getMessages()) {
               sb.append(" ").append(message.getLang()).append(" : ").append(message.getValue());
            }
         }

         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_KGSS, sb.toString());
      }
   }

   public GetKeyResponseContent getKey(GetKeyRequestContent request, Credential encryption, Credential service, Element samlAssertion, Map<String, PrivateKey> decryptionKeys, byte[] etkKGSS) throws TechnicalConnectorException {
      SAMLToken token = SAMLTokenFactory.getInstance().createSamlToken(samlAssertion, service);
      KgssMessageBuilder builder = new KgssMessageBuilderImpl(etkKGSS, encryption, decryptionKeys);
      GetKeyRequest sealedRequest = builder.sealGetKeyRequest(request);
      GenericRequest genericRequest = ServiceFactory.getKGSSServiceSecured(token);
      genericRequest.setPayload(sealedRequest);

      try {
         GetKeyResponse response = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(genericRequest).asObject(GetKeyResponse.class);
         checkReplyStatus(response.getStatus().getCode());
         this.checkErrorMessages(response.getErrors());
         return builder.unsealGetKeyResponse(response);
      } catch (SOAPException ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex.getMessage(), ex);
      }
   }

   private static boolean checkReplyStatus(String responseCode) throws TechnicalConnectorException {
      if (!"100".equals(responseCode) && !"200".equals(responseCode)) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, "Received error from eHealth KGSS Web Service");
      } else {
         return true;
      }
   }

   @Override
   public KeyResult retrieveKeyFromKgss(KeyStore keystore, SAMLToken samlToken, String passPhrase, byte[] keyId, byte[] myEtk, byte[] kgssEtk) throws TechnicalConnectorException {
      GetKeyRequestContent getKeyRequestContent = new GetKeyRequestContent();
      SecretKey key = null;

      KeyStoreCredential credential = new KeyStoreCredential(keystore, "authentication", passPhrase);
      Map<String, PrivateKey> hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray());


      if (myEtk != null) {
         // Mode1 : using ETK
         getKeyRequestContent.setETK(myEtk);
      } else {
         // Using sym Key
         try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            synchronized (keyGen) {
               key = keyGen.generateKey();
            }
         } catch (Exception e) {
            throw new IllegalStateException("error.technical", e);
         }
         getKeyRequestContent.setKeyEncryptionKey(key.getEncoded());
      }

      getKeyRequestContent.setKeyIdentifier(Base64.decode(keyId));

      KeyResult keyResultToReturn;

      try {
         GetKeyResponseContent getKeyResponseContent = this.getKey(getKeyRequestContent, credential, credential, samlToken.getAssertion(), hokPrivateKeys, kgssEtk);
         keyResultToReturn = new KeyResult(new SecretKeySpec(getKeyResponseContent.getKey(), "AES"), new String(keyId));
      } catch (SOAPFaultException se) {
         if (se.getFault() != null && se.getFault().getFaultCode() != null && se.getFault().getFaultCode().contains("InvalidSecurity")) {
            throw new IllegalArgumentException("error.kgss.getKey", se);
         } else {
            throw new IllegalArgumentException("error.kgss.getKey.other", se);
         }
      } catch (TechnicalConnectorException e) {
         throw new IllegalArgumentException("technical.connector.error.retrieve.key", e);
      }

      return keyResultToReturn;
   }

   /* (non-Javadoc)
    * @see be.technicalconnector.services.KgssService#retrieveNewKey(byte[], java.util.List, java.lang.String, java.lang.String, java.lang.String, byte[])
    */
   @Override
   public KeyResult retrieveNewKey(KeyStore keystore, SAMLToken samlToken, String passPhrase, byte[] etkKgss, List<String> credentialTypes, String prescriberId, String executorId, String patientId, byte[] myEtk) throws TechnicalConnectorException {
      GetNewKeyRequestContent req = new GetNewKeyRequestContent();
      req.setETK(myEtk);

      // --- Building the Access Control List
      List<CredentialType> allowedReaders = req.getAllowedReaders();

      KeyStoreCredential credential = new KeyStoreCredential(keystore, "authentication", passPhrase);
      Map<String, PrivateKey> hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray());

      for (String credentialTypeStr : credentialTypes) {
         String[] atrs = credentialTypeStr.split(",");
         if (atrs.length != 3 && atrs.length != 2) {
            throw new IllegalArgumentException("Invalid property : kgss.createPrescription.ACL.XXX = " + credentialTypeStr);
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

      KeyResult keyResultToReturn = null;

      try {

         GetNewKeyResponseContent getNewKeyResponseContent = getNewKey(req, credential, hokPrivateKeys, etkKgss);
         byte[] keyResponse = getNewKeyResponseContent.getNewKey();
         byte[] keyId = getNewKeyResponseContent.getNewKeyIdentifier();

         keyResultToReturn = new KeyResult(new SecretKeySpec(keyResponse, "AES"), new String(Base64.encode(keyId)));
      } catch (TechnicalConnectorException e) {
         log.error("Error retrieving new key", e);

         if (e instanceof UnsealConnectorException) {
            if (((UnsealConnectorException) e).getUnsealResult() != null) {
               List<NotificationError> decryptionFailure = ((UnsealConnectorException) e).getUnsealResult().getErrors();
               for (NotificationError error : decryptionFailure) {
                  log.error("NotificationError: " + error.toString());
               }
               List<NotificationWarning> warnings = ((UnsealConnectorException) e).getUnsealResult().getWarnings();
               for (NotificationWarning warning : warnings) {
                  log.error("NotificationWarning: " + warning.toString());
               }
            }
         }
         throw new IllegalArgumentException("technical.connector.error.retrieve.new.key", e);
      }
      return keyResultToReturn;
   }



   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetKeyRequest.class);
      JaxbContextFactory.initJaxbContext(GetKeyResponse.class);
      JaxbContextFactory.initJaxbContext(GetNewKeyRequest.class);
      JaxbContextFactory.initJaxbContext(GetNewKeyResponse.class);
   }
}
