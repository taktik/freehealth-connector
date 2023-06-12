package be.ehealth.businessconnector.mycarenet.agreement.builders.impl;

import be.cin.encrypted.EncryptedKnownContent;
import be.ehealth.business.mycarenetcommons.v3.mapper.BlobMapper;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.businessconnector.mycarenet.agreement.builders.ResponseObjectBuilder;
import be.ehealth.businessconnector.mycarenet.agreement.domain.AgreementBuilderRequest;
import be.ehealth.businessconnector.mycarenet.agreement.domain.AskAgreementBuilderRequest;
import be.ehealth.businessconnector.mycarenet.agreement.domain.ConsultAgreementBuilderRequest;
import be.ehealth.businessconnector.mycarenet.agreement.domain.SignedEncryptedBuilderResponse;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.Crypto.SigningPolicySelector;
import be.ehealth.technicalconnector.service.etee.domain.UnsealedData;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementResponse;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
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
   public ResponseObjectBuilderImpl() {
   }

   public SignedEncryptedBuilderResponse handleAskAgreementResponse(AskAgreementResponse response, AskAgreementBuilderRequest request) throws TechnicalConnectorException {
      return this.handleResponse(response, request);
   }

   public SignedEncryptedBuilderResponse handleConsultAgreementResponse(ConsultAgreementResponse response, ConsultAgreementBuilderRequest request) throws TechnicalConnectorException {
      return this.handleResponse(response, request);
   }

   private SignedEncryptedBuilderResponse handleResponse(SendResponseType sendResponse, AgreementBuilderRequest sendRequestBuilder) throws TechnicalConnectorException {
      if (sendResponse == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"sendResponse"});
      } else {
         BlobType blobType = sendResponse.getReturn().getDetail();
         byte[] unsealedData = this.getUnsealedData(BlobMapper.mapBlobfromBlobType(blobType));
         EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)(new MarshallerHelper(EncryptedKnownContent.class, EncryptedKnownContent.class)).toObject(unsealedData);
         byte[] businessContent = ArrayUtils.clone(encryptedKnownContent.getBusinessContent().getValue());
         if (encryptedKnownContent.getXades() != null) {
            byte[] signature = encryptedKnownContent.getXades();
            SignatureVerificationResult signatureVerificationResult = this.verifySignature(signature, this.appendRequestToDataToVerify(encryptedKnownContent, sendRequestBuilder.getBusinessContent()));
            return new SignedEncryptedBuilderResponse(encryptedKnownContent, signatureVerificationResult, unsealedData, ArrayUtils.clone(signature), businessContent);
         } else {
            SignatureVerificationResult signatureVerificationResult = new SignatureVerificationResult();
            signatureVerificationResult.getErrors().add(SignatureVerificationError.SIGNATURE_NOT_PRESENT);
            return new SignedEncryptedBuilderResponse(encryptedKnownContent, signatureVerificationResult, unsealedData, (byte[])null, businessContent);
         }
      }
   }

   private byte[] getUnsealedData(Blob blob) throws TechnicalConnectorException {
      Crypto crypto = SessionUtil.getHolderOfKeyCrypto();
      UnsealedData unsealedData = crypto.unseal(SigningPolicySelector.WITHOUT_NON_REPUDIATION, blob.getContent());
      return unsealedData.getContentAsByte();
   }

   private SignatureVerificationResult verifySignature(byte[] signature, byte[] signedData) throws TechnicalConnectorException {
      Map<String, Object> options = new HashMap();
      options.put("followNestedManifest", true);
      SignatureBuilder builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T);
      return builder.verify(signedData, signature, options);
   }

   private byte[] appendRequestToDataToVerify(Object dataToVerify, Object request) throws TechnicalConnectorException {
      Document explodedDoc = ConnectorXmlUtils.toDocument(dataToVerify);
      Node firstDocImportedNode = explodedDoc.importNode(ConnectorXmlUtils.toElement(ConnectorXmlUtils.toByteArray(request)), true);
      ConnectorXmlUtils.getFirstChildElement(explodedDoc).appendChild(firstDocImportedNode);
      return ConnectorXmlUtils.toByteArray(explodedDoc);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(new Class[]{BlobType.class});
      JaxbContextFactory.initJaxbContext(new Class[]{SendResponseType.class});
      JaxbContextFactory.initJaxbContext(new Class[]{EncryptedKnownContent.class});
   }
}
