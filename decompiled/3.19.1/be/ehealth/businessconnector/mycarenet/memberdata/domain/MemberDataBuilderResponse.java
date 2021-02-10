package be.ehealth.businessconnector.mycarenet.memberdata.domain;

import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;
import oasis.names.tc.saml._2_0.protocol.Response;

public class MemberDataBuilderResponse {
   private MemberDataConsultationResponse consultationResponse;
   private Response response;
   private Map<String, SignatureVerificationResult> signatureVerificationResults;

   public MemberDataBuilderResponse(MemberDataConsultationResponse consultationResponse, Response response, Map<String, SignatureVerificationResult> signatureVerificationResults) {
      this.consultationResponse = consultationResponse;
      this.response = response;
      this.signatureVerificationResults = signatureVerificationResults;
   }

   public MemberDataConsultationResponse getConsultationResponse() {
      return this.consultationResponse;
   }

   public Response getResponse() {
      return this.response;
   }

   public Map<String, SignatureVerificationResult> getSignatureVerificationResult() {
      return this.signatureVerificationResults;
   }
}
