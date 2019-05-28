package be.ehealth.businessconnector.genericasync.domain;

import be.cin.nip.async.generic.TAckResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public final class ProcessedTAckResponse {
   private TAckResponse tAckResponse;
   private SignatureVerificationResult signatureVerificationResult;

   public ProcessedTAckResponse(TAckResponse tAckResponse, SignatureVerificationResult signatureVerificationResult) {
      this.tAckResponse = tAckResponse;
      this.signatureVerificationResult = signatureVerificationResult;
   }

   public TAckResponse getTAckResponse() {
      return this.tAckResponse;
   }

   public SignatureVerificationResult getSignatureVerificationResult() {
      return this.signatureVerificationResult;
   }
}
