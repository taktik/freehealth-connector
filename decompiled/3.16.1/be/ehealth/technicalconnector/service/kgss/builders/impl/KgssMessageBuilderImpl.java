package be.ehealth.technicalconnector.service.kgss.builders.impl;

import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.CryptoFactory;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.etee.domain.UnsealedData;
import be.ehealth.technicalconnector.service.kgss.builders.KgssMessageBuilder;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.utils.ConnectorCryptoUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequest;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponse;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequest;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponse;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.SealedContentType;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.text.MessageFormat;
import java.util.Map;
import javax.crypto.SecretKey;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KgssMessageBuilderImpl implements KgssMessageBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(KgssMessageBuilderImpl.class);
   private SecretKey key;
   private EncryptionToken encryptionToken;
   private Crypto crypto;
   private static MarshallerHelper<GetNewKeyResponseContent, GetNewKeyRequestContent> newKeyHelper = new MarshallerHelper(GetNewKeyResponseContent.class, GetNewKeyRequestContent.class);
   private static MarshallerHelper<GetKeyResponseContent, GetKeyRequestContent> getKeyHelper = new MarshallerHelper(GetKeyResponseContent.class, GetKeyRequestContent.class);

   public KgssMessageBuilderImpl() {
      LOG.debug("KgssMessageBuilderImpl default consturctor. Only for bootstrap purspose");
   }

   public KgssMessageBuilderImpl(byte[] etkKgss, Credential encryptionCredential, Map<String, PrivateKey> decryptionKeys) throws TechnicalConnectorException {
      this.encryptionToken = this.toEncryptionToken(etkKgss);
      this.crypto = CryptoFactory.getCrypto(encryptionCredential, decryptionKeys);
   }

   public GetNewKeyRequest sealGetNewKeyRequest(GetNewKeyRequestContent requestContent) throws TechnicalConnectorException {
      GetNewKeyRequest request = new GetNewKeyRequest();
      byte[] xmlByteArray = newKeyHelper.toXMLByteArray(requestContent);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Access Control List defined as : " + new String(xmlByteArray));
      }

      SealedContentType type = new SealedContentType();
      type.setSealedContent(this.crypto.seal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, this.encryptionToken, xmlByteArray));
      request.setSealedNewKeyRequest(type);
      return request;
   }

   public GetNewKeyResponseContent unsealGetNewKeyResponse(GetNewKeyResponse response) throws TechnicalConnectorException {
      SealedContentType sealedResponse = response.getSealedNewKeyResponse();
      byte[] decrypted = this.crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, sealedResponse.getSealedContent()).getContentAsByte();
      return (GetNewKeyResponseContent)newKeyHelper.toObject(decrypted);
   }

   public GetKeyRequest sealGetKeyRequest(GetKeyRequestContent requestContent) throws TechnicalConnectorException {
      GetKeyRequest request = new GetKeyRequest();
      if (ArrayUtils.isEmpty(requestContent.getETK()) && ArrayUtils.isEmpty(requestContent.getKeyEncryptionKey())) {
         this.key = ConnectorCryptoUtils.generateKey();
         requestContent.setKeyEncryptionKey(this.key.getEncoded());
      }

      byte[] xmlByteArray = getKeyHelper.toXMLByteArray(requestContent);
      SealedContentType type = new SealedContentType();
      type.setSealedContent(this.crypto.seal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, this.encryptionToken, xmlByteArray));
      request.setSealedKeyRequest(type);
      return request;
   }

   public GetKeyResponseContent unsealGetKeyResponse(GetKeyResponse response) throws TechnicalConnectorException {
      SealedContentType sealedResponse = response.getSealedKeyResponse();
      UnsealedData decrypted = null;
      if (this.key == null) {
         decrypted = this.crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, sealedResponse.getSealedContent());
      } else {
         decrypted = this.crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, new KeyResult(this.key, "dummy"), sealedResponse.getSealedContent());
      }

      return (GetKeyResponseContent)getKeyHelper.toObject(decrypted.getContentAsByte());
   }

   private EncryptionToken toEncryptionToken(byte[] etk) throws TechnicalConnectorException {
      try {
         return new EncryptionToken(etk);
      } catch (GeneralSecurityException var3) {
         LOG.debug(MessageFormat.format(TechnicalConnectorExceptionValues.ERROR_KGSS.getMessage(), "Not a valid ETK, expecting the KGSS ETK."));
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_KGSS, var3, new Object[]{"Not a valid ETK, expecting the KGSS ETK."});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetNewKeyRequestContent.class);
      JaxbContextFactory.initJaxbContext(GetNewKeyResponseContent.class);
      JaxbContextFactory.initJaxbContext(GetKeyRequestContent.class);
      JaxbContextFactory.initJaxbContext(GetKeyResponseContent.class);
   }
}
