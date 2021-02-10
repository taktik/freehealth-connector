package be.ehealth.businessconnector.genericasync.domain;

import be.cin.encrypted.EncryptedKnownContent;
import be.cin.nip.async.generic.MsgResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class ProcessedMsgResponse<T> {
   private T businessResponse;
   private EncryptedKnownContent encryptedKnownContent;
   private SignatureVerificationResult signatureVerificationResult;
   private byte[] signedData;
   private MsgResponse msgResponse;

   public ProcessedMsgResponse(MsgResponse msgResponse, T businessResponse, EncryptedKnownContent encryptedKnownContent, SignatureVerificationResult signatureVerificationResult, byte[] signedData) {
      this(msgResponse, businessResponse, signatureVerificationResult, signedData);
      this.encryptedKnownContent = encryptedKnownContent;
   }

   public ProcessedMsgResponse(MsgResponse msgResponse, T businessResponse, SignatureVerificationResult signatureVerificationResult, byte[] signedData) {
      this.businessResponse = businessResponse;
      this.signatureVerificationResult = signatureVerificationResult;
      this.signedData = signedData;
      this.msgResponse = msgResponse;
   }

   public T getBusinessResponse() {
      return this.businessResponse;
   }

   public void setBusinessResponse(T businessResponse) {
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

   public MsgResponse getMsgResponse() {
      return this.msgResponse;
   }
}
