package be.ehealth.businessconnector.mycarenet.attest.domain;

import be.fgov.ehealth.messageservices.core.v1.SendTransactionResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class AttestBuilderResponse {
   private SendTransactionResponse sendTransactionResponse;
   private SignatureVerificationResult signatureVerificationResult;

   public AttestBuilderResponse(SendTransactionResponse sendTransactionResponse, SignatureVerificationResult signatureVerificationResult) {
      this.sendTransactionResponse = sendTransactionResponse;
      this.signatureVerificationResult = signatureVerificationResult;
   }

   public SendTransactionResponse getSendTransactionResponse() {
      return this.sendTransactionResponse;
   }

   public SignatureVerificationResult getSignatureVerificationResult() {
      return this.signatureVerificationResult;
   }
}
