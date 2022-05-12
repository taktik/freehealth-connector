package be.ehealth.businessconnector.mycarenet.attest.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.businessconnector.mycarenet.attest.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.mycarenet.attest.domain.AttestBuilderResponse;
import be.ehealth.businessconnector.mycarenet.attest.mappers.BlobMapper;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public ResponseObjectBuilderImpl() {
   }

   public final AttestBuilderResponse handleSendResponseType(SendResponseType sendResponse) throws TechnicalConnectorException, UnsupportedEncodingException {
      BlobType blobType = sendResponse.getReturn().getDetail();
      Blob blob = BlobMapper.mapBlobfromBlobType(blobType);
      return this.createAttestBuilderResponse(blob);
   }

   private AttestBuilderResponse createAttestBuilderResponse(Blob blob) throws TechnicalConnectorException {
      byte[] unsealedData = SessionUtil.getHolderOfKeyCrypto().unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, blob.getContent()).getContentAsByte();
      EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)(new MarshallerHelper(EncryptedKnownContent.class, EncryptedKnownContent.class)).toObject(unsealedData);
      byte[] xades = encryptedKnownContent.getXades();
      SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES);
      Map<String, Object> options = Collections.emptyMap();
      SignatureVerificationResult signatureVerificationResult = builder.verify(ConnectorXmlUtils.toByteArray((Object)encryptedKnownContent), xades, options);
      return new AttestBuilderResponse(encryptedKnownContent, signatureVerificationResult, unsealedData, ArrayUtils.clone(xades));
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(SendAttestationResponse.class);
      JaxbContextFactory.initJaxbContext(SendResponseType.class);
      JaxbContextFactory.initJaxbContext(Kmehrmessage.class);
   }
}
