package be.ehealth.businessconnector.mycarenet.attestv2.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.mycarenetcommons.mapper.v3.BlobMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.businessconnector.mycarenet.attestv2.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.SendAttestBuilderRequest;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.SignedBuilderResponse;
import be.ehealth.businessconnector.mycarenet.attestv2.domain.SignedEncryptedBuilderResponse;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.UnsealedData;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.Kmehrmessage;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public final SignedEncryptedBuilderResponse handleSendResponseType(SendResponseType sendResponse, SendAttestBuilderRequest builderRequest) throws TechnicalConnectorException {
      if (sendResponse == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"sendResponse"});
      } else if (builderRequest == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"builderRequest"});
      } else if (builderRequest.getBusinessContent() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"builderRequest.getBusinessContent"});
      } else {
         BlobType blobType = sendResponse.getReturn().getDetail();
         Blob blob = BlobMapper.mapBlobfromBlobType(blobType);
         byte[] unsealedData = this.getUnsealedData(blob);
         EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)(new MarshallerHelper(EncryptedKnownContent.class, EncryptedKnownContent.class)).toObject(unsealedData);
         if (encryptedKnownContent.getXades() != null) {
            byte[] signature = encryptedKnownContent.getXades();
            SignatureVerificationResult signatureVerificationResult = this.verifySignature(signature, this.appendRequestToDataToVerify(encryptedKnownContent, builderRequest.getBusinessContent()));
            return new SignedEncryptedBuilderResponse(encryptedKnownContent, signatureVerificationResult, unsealedData, ArrayUtils.clone(signature));
         } else {
            SignatureVerificationResult signatureVerificationResult = new SignatureVerificationResult();
            signatureVerificationResult.getErrors().add(SignatureVerificationError.SIGNATURE_NOT_PRESENT);
            return new SignedEncryptedBuilderResponse(encryptedKnownContent, signatureVerificationResult, unsealedData, (byte[])null);
         }
      }
   }

   public SignedBuilderResponse handleCancelResponseType(SendResponseType sendResponse, CancelAttestationRequest cancelAttestationRequest) throws TechnicalConnectorException {
      if (sendResponse == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"sendResponse"});
      } else if (cancelAttestationRequest == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"cancelAttestationRequest"});
      } else if (sendResponse.getReturn().getXadesT() != null && sendResponse.getReturn().getXadesT().getValue() != null) {
         byte[] signature = sendResponse.getReturn().getXadesT().getValue();
         SignatureVerificationResult signatureVerificationResult = this.verifySignature(signature, this.appendRequestToDataToVerify(sendResponse, cancelAttestationRequest));
         return new SignedBuilderResponse(signature, sendResponse.getReturn().getDetail().getValue(), signatureVerificationResult);
      } else {
         SignatureVerificationResult signatureVerificationResult = new SignatureVerificationResult();
         signatureVerificationResult.getErrors().add(SignatureVerificationError.SIGNATURE_NOT_PRESENT);
         return new SignedBuilderResponse(sendResponse.getReturn().getDetail().getValue(), signatureVerificationResult);
      }
   }

   private byte[] appendRequestToDataToVerify(Object dataToVerify, Object request) throws TechnicalConnectorException {
      Document explodedDoc = ConnectorXmlUtils.toDocument(dataToVerify);
      Node firstDocImportedNode = explodedDoc.importNode(ConnectorXmlUtils.toElement(ConnectorXmlUtils.toByteArray(request)), true);
      ConnectorXmlUtils.getFirstChildElement(explodedDoc).appendChild(firstDocImportedNode);
      return ConnectorXmlUtils.toByteArray((Node)explodedDoc);
   }

   private byte[] getUnsealedData(Blob blob) throws TechnicalConnectorException {
      Crypto crypto = SessionUtil.getHolderOfKeyCrypto();
      UnsealedData unsealedData = crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, blob.getContent());
      return unsealedData.getContentAsByte();
   }

   private SignatureVerificationResult verifySignature(byte[] signature, byte[] signedData) throws TechnicalConnectorException {
      Map<String, Object> options = new HashMap();
      options.put("followNestedManifest", true);
      SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T);
      return builder.verify((byte[])signedData, (byte[])signature, options);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(SendAttestationResponse.class);
      JaxbContextFactory.initJaxbContext(CancelAttestationResponse.class);
      JaxbContextFactory.initJaxbContext(SendResponseType.class);
      JaxbContextFactory.initJaxbContext(Kmehrmessage.class);
   }
}
