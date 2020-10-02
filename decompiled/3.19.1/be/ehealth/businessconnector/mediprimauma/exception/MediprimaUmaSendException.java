package be.ehealth.businessconnector.mediprimauma.exception;

import be.fgov.ehealth.mediprima.uma.protocol.v1.SendUrgentMedicalAidAttestationResponse;

public class MediprimaUmaSendException extends MediprimaUmaException {
   private static final long serialVersionUID = 1L;
   private final SendUrgentMedicalAidAttestationResponse response;

   public MediprimaUmaSendException(SendUrgentMedicalAidAttestationResponse response) {
      super(response);
      this.response = response;
   }

   public SendUrgentMedicalAidAttestationResponse getResponse() {
      return this.response;
   }
}
