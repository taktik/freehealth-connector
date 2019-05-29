package be.ehealth.businessconnector.mycarenet.attestv2.domain;

import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class SignedBuilderResponse {
   private SignatureVerificationResult signatureVerificationResult;
   private byte[] signedData;
   private SendTransactionResponse sendTransactionResponse;

   public SignedBuilderResponse(byte[] response, SignatureVerificationResult signatureVerificationResult, byte[] signedData) {
      this.signatureVerificationResult = signatureVerificationResult;
      this.signedData = signedData;
      this.sendTransactionResponse = (SendTransactionResponse)ConnectorXmlUtils.toObject(response, SendTransactionResponse.class);
   }

   public SignatureVerificationResult getSignatureVerificationResult() {
      return this.signatureVerificationResult;
   }

   public byte[] getSignedData() {
      return this.signedData;
   }

   public SendTransactionResponse getSendTransactionResponse() {
      return this.sendTransactionResponse;
   }
}
