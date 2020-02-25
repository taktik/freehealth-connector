package org.taktik.connector.technical.service.kgss.builders.impl;

import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.etee.CryptoFactory;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.etee.domain.UnsealedData;
import org.taktik.connector.technical.service.kgss.builders.KgssMessageBuilder;
import org.taktik.connector.technical.service.kgss.domain.KeyResult;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.utils.ConnectorCryptoUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
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

   public KgssMessageBuilderImpl(byte[] etkKgss, Crypto crypto, Credential encryptionCredential, Map<String, PrivateKey> decryptionKeys) throws TechnicalConnectorException {
      this.crypto = crypto;
      this.encryptionToken = this.toEncryptionToken(etkKgss);
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
      return newKeyHelper.toObject(decrypted);
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
      UnsealedData decrypted;
      if (this.key == null) {
         decrypted = this.crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, sealedResponse.getSealedContent());
      } else {
         decrypted = this.crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, new KeyResult(this.key, "dummy"), sealedResponse.getSealedContent());
      }

      return getKeyHelper.toObject(decrypted.getContentAsByte());
   }

   private EncryptionToken toEncryptionToken(byte[] etk) throws TechnicalConnectorException {
      try {
         return new EncryptionToken(etk);
      } catch (GeneralSecurityException var3) {
         LOG.debug(MessageFormat.format(TechnicalConnectorExceptionValues.ERROR_KGSS.getMessage(), "Not a valid ETK, expecting the KGSS ETK."));
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_KGSS, var3, "Not a valid ETK, expecting the KGSS ETK.");
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetNewKeyRequestContent.class);
      JaxbContextFactory.initJaxbContext(GetNewKeyResponseContent.class);
      JaxbContextFactory.initJaxbContext(GetKeyRequestContent.class);
      JaxbContextFactory.initJaxbContext(GetKeyResponseContent.class);
   }
}
