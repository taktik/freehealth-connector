package be.ehealth.businessconnector.mediprimauma.exception;

import be.fgov.ehealth.mediprima.uma.protocol.v1.SearchUrgentMedicalAidAttestationResponse;

public class MediprimaUmaSearchException extends MediprimaUmaException {
   private static final long serialVersionUID = 1L;
   private final SearchUrgentMedicalAidAttestationResponse response;

   public MediprimaUmaSearchException(SearchUrgentMedicalAidAttestationResponse response) {
      super(response);
      this.response = response;
   }

   public SearchUrgentMedicalAidAttestationResponse getResponse() {
      return this.response;
   }
}
