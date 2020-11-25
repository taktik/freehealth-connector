package be.ehealth.businessconnector.mycarenet.attestv2.domain;

import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class SignedBuilderResponse {
   private byte[] xadesT;
   private byte[] signedData;
   private SignatureVerificationResult signatureVerificationResult;
   private SendTransactionResponse sendTransactionResponse;

   public SignedBuilderResponse(byte[] signedData, SignatureVerificationResult signatureVerificationResult) {
      this((byte[])null, signedData, signatureVerificationResult);
   }

   public SignedBuilderResponse(byte[] xadesT, byte[] signedData, SignatureVerificationResult signatureVerificationResult) {
      this.xadesT = xadesT;
      this.signedData = signedData;
      this.signatureVerificationResult = signatureVerificationResult;
      this.sendTransactionResponse = (SendTransactionResponse)ConnectorXmlUtils.toObject(signedData, SendTransactionResponse.class);
   }

   public byte[] getXadesT() {
      return this.xadesT;
   }

   public byte[] getSignedData() {
      return this.signedData;
   }

   public SignatureVerificationResult getSignatureVerificationResult() {
      return this.signatureVerificationResult;
   }

   public SendTransactionResponse getSendTransactionResponse() {
      return this.sendTransactionResponse;
   }
}
