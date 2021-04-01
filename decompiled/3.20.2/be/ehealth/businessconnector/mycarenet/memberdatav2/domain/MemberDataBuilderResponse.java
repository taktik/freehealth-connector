package be.ehealth.businessconnector.mycarenet.memberdatav2.domain;

import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;

public class MemberDataBuilderResponse {
   private MemberDataConsultationResponse consultationResponse;
   private byte[] response;
   private Map<String, SignatureVerificationResult> signatureVerificationResults;

   public MemberDataBuilderResponse(MemberDataConsultationResponse consultationResponse, byte[] response, Map<String, SignatureVerificationResult> signatureVerificationResults) {
      this.consultationResponse = consultationResponse;
      this.response = response;
      this.signatureVerificationResults = signatureVerificationResults;
   }

   public MemberDataConsultationResponse getConsultationResponse() {
      return this.consultationResponse;
   }

   public byte[] getResponse() {
      return this.response;
   }

   public Map<String, SignatureVerificationResult> getSignatureVerificationResult() {
      return this.signatureVerificationResults;
   }
}
