package be.ehealth.businessconnector.mediprimauma.exception;

import be.fgov.ehealth.mediprima.uma.protocol.v1.DeleteUrgentMedicalAidAttestationResponse;

public class MediprimaUmaDeleteException extends MediprimaUmaException {
   private static final long serialVersionUID = 1L;
   private final DeleteUrgentMedicalAidAttestationResponse response;

   public MediprimaUmaDeleteException(DeleteUrgentMedicalAidAttestationResponse response) {
      super(response);
      this.response = response;
   }

   public DeleteUrgentMedicalAidAttestationResponse getResponse() {
      return this.response;
   }
}
