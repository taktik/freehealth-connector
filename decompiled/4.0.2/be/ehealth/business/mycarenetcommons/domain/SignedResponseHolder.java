package be.ehealth.business.mycarenetcommons.domain;

import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class SignedResponseHolder {
   private byte[] xadesT;
   private byte[] businessResponse;
   private SignatureVerificationResult signatureVerificationResult;

   public SignedResponseHolder(byte[] businessResponse, SignatureVerificationResult signatureVerificationResult) {
      this((byte[])null, businessResponse, signatureVerificationResult);
   }

   public SignedResponseHolder(byte[] xadesT, byte[] businessResponse, SignatureVerificationResult signatureVerificationResult) {
      this.xadesT = xadesT;
      this.businessResponse = businessResponse;
      this.signatureVerificationResult = signatureVerificationResult;
   }

   public byte[] getXadesT() {
      return this.xadesT;
   }

   public byte[] getBusinessResponse() {
      return this.businessResponse;
   }

   public SignatureVerificationResult getSignatureVerificationResult() {
      return this.signatureVerificationResult;
   }
}
