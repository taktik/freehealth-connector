package org.taktik.connector.technical.service.kgss.impl;

import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.kgss.KgssService;
import org.taktik.connector.technical.service.kgss.builders.KgssMessageBuilder;
import org.taktik.connector.technical.service.kgss.builders.impl.KgssMessageBuilderImpl;
import org.taktik.connector.technical.service.kgss.domain.KeyResult;
import org.taktik.connector.technical.service.sts.SAMLTokenFactory;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
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
import java.security.PrivateKey;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.soap.SOAPException;
import org.bouncycastle.util.encoders.Base64;
import org.w3c.dom.Element;

public class KgssServiceImpl implements KgssService, ConfigurationModuleBootstrap.ModuleBootstrapHook {

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

   public static boolean checkReplyStatus(String responseCode) throws TechnicalConnectorException {
      if (!"100".equals(responseCode) && !"200".equals(responseCode)) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, "Received error from eHealth KGSS Web Service");
      } else {
         return true;
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetKeyRequest.class);
      JaxbContextFactory.initJaxbContext(GetKeyResponse.class);
      JaxbContextFactory.initJaxbContext(GetNewKeyRequest.class);
      JaxbContextFactory.initJaxbContext(GetNewKeyResponse.class);
   }
}
