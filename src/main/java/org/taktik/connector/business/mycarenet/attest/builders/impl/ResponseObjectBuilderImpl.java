package org.taktik.connector.business.mycarenet.attest.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenet.attest.builders.ResponseObjectBuilder;
import org.taktik.connector.business.mycarenet.attest.domain.AttestBuilderResponse;
import org.taktik.connector.business.mycarenet.attest.mappers.BlobMapper;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.SessionUtil;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
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

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public final AttestBuilderResponse handleSendResponseType(SendResponseType sendResponse, SendAttestationRequest sendRequest) throws TechnicalConnectorException, UnsupportedEncodingException {
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
      SignatureVerificationResult signatureVerificationResult = builder.verify(unsealedData, xades, options);
      SendTransactionResponse sendTransactionResponse = (SendTransactionResponse)(new MarshallerHelper(SendTransactionResponse.class, SendTransactionResponse.class)).toObject(encryptedKnownContent.getBusinessContent().getValue());
      return new AttestBuilderResponse(sendTransactionResponse, signatureVerificationResult);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(SendAttestationResponse.class);
      JaxbContextFactory.initJaxbContext(SendResponseType.class);
      JaxbContextFactory.initJaxbContext(Kmehrmessage.class);
   }
}
