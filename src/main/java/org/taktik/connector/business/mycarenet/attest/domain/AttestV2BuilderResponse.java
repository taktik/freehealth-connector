package org.taktik.connector.business.mycarenet.attest.domain;

import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class AttestV2BuilderResponse {
   private SendTransactionResponse sendTransactionResponse;
   private SignatureVerificationResult signatureVerificationResult;

   public AttestV2BuilderResponse(SendTransactionResponse sendTransactionResponse, SignatureVerificationResult signatureVerificationResult) {
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
