package be.ehealth.businessconnector.mycarenet.agreement.domain;

import be.cin.encrypted.EncryptedKnownContent;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class SignedEncryptedBuilderResponse {
   private EncryptedKnownContent encryptedKnownContent;
   private SignatureVerificationResult signatureVerificationResult;
   private byte[] signedData;
   private byte[] xades;
   private byte[] businessResponse;

   public SignedEncryptedBuilderResponse(EncryptedKnownContent encryptedKnownContent, SignatureVerificationResult signatureVerificationResult, byte[] signedData, byte[] xades, byte[] businessResponse) {
      this.encryptedKnownContent = encryptedKnownContent;
      this.signatureVerificationResult = signatureVerificationResult;
      this.signedData = signedData;
      this.xades = xades;
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

   public byte[] getXades() {
      return this.xades;
   }

   public byte[] getBusinessResponse() {
      return this.businessResponse;
   }
}
