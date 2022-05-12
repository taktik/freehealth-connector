package be.ehealth.business.mycarenetcommons.v4.builders;

import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.mycarenetcommons.domain.EncryptedRequestHolder;
import be.ehealth.business.mycarenetcommons.domain.SignedEncryptedResponseHolder;
import be.ehealth.business.mycarenetcommons.domain.SignedResponseHolder;
import be.ehealth.business.mycarenetcommons.v4.mapper.BlobMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.UnsealedData;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.mycarenet.commons.core.v4.BlobType;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType;
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

public class ResponseObjectBuilderHelper {
   public ResponseObjectBuilderHelper() {
   }

   public final SignedEncryptedResponseHolder build(SendResponseType sendResponse, EncryptedRequestHolder builderRequest) throws TechnicalConnectorException {
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
         byte[] businessContent = ArrayUtils.clone(encryptedKnownContent.getBusinessContent().getValue());
         if (encryptedKnownContent.getXades() != null) {
            byte[] signature = encryptedKnownContent.getXades();
            SignatureVerificationResult signatureVerificationResult = this.verifySignature(signature, this.appendRequestToDataToVerify(encryptedKnownContent, builderRequest.getBusinessContent()));
            return new SignedEncryptedResponseHolder(encryptedKnownContent, signatureVerificationResult, unsealedData, ArrayUtils.clone(signature), businessContent);
         } else {
            SignatureVerificationResult signatureVerificationResult = new SignatureVerificationResult();
            signatureVerificationResult.getErrors().add(SignatureVerificationError.SIGNATURE_NOT_PRESENT);
            return new SignedEncryptedResponseHolder(encryptedKnownContent, signatureVerificationResult, unsealedData, (byte[])null, businessContent);
         }
      }
   }

   public SignedResponseHolder build(SendResponseType sendResponse, SendRequestType builderRequest) throws TechnicalConnectorException {
      if (sendResponse == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"sendResponse"});
      } else if (builderRequest == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"cancelAttestationRequest"});
      } else if (sendResponse.getReturn().getXadesT() != null && sendResponse.getReturn().getXadesT().getValue() != null) {
         byte[] signature = sendResponse.getReturn().getXadesT().getValue();
         SignatureVerificationResult signatureVerificationResult = this.verifySignature(signature, this.appendRequestToDataToVerify(sendResponse, builderRequest));
         return new SignedResponseHolder(signature, sendResponse.getReturn().getDetail().getValue(), signatureVerificationResult);
      } else {
         SignatureVerificationResult signatureVerificationResult = new SignatureVerificationResult();
         signatureVerificationResult.getErrors().add(SignatureVerificationError.SIGNATURE_NOT_PRESENT);
         return new SignedResponseHolder(sendResponse.getReturn().getDetail().getValue(), signatureVerificationResult);
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
}
