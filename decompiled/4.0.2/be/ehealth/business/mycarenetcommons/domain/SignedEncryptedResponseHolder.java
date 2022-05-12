package be.ehealth.business.mycarenetcommons.domain;

import be.cin.encrypted.EncryptedKnownContent;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class SignedEncryptedResponseHolder {
   private EncryptedKnownContent encryptedKnownContent;
   private SignatureVerificationResult signatureVerificationResult;
   private byte[] signedData;
   private byte[] xadesT;
   private byte[] businessResponse;

   public SignedEncryptedResponseHolder(EncryptedKnownContent encryptedKnownContent, SignatureVerificationResult signatureVerificationResult, byte[] signedData, byte[] xadesT, byte[] businessResponse) {
      this.encryptedKnownContent = encryptedKnownContent;
      this.signatureVerificationResult = signatureVerificationResult;
      this.signedData = signedData;
      this.xadesT = xadesT;
      this.businessResponse = businessResponse;
   }

   public EncryptedKnownContent getRawDecryptedBlob() {
      return this.encryptedKnownContent;
   }

   public SignatureVerificationResult getSignatureVerificationResult() {
      return this.signatureVerificationResult;
   }

   public byte[] getSignedData() {
      return this.signedData;
   }

   public byte[] getXadesT() {
      return this.xadesT;
   }

   public byte[] getBusinessResponse() {
      return this.businessResponse;
   }
}
