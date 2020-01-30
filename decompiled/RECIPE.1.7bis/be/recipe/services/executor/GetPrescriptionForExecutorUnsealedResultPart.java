package be.recipe.services.executor;

public class GetPrescriptionForExecutorUnsealedResultPart {
   private String patientId;
   private String prescriptionType;

   public GetPrescriptionForExecutorUnsealedResultPart(String patientId, String prescriptionType) {
      this.patientId = patientId;
      this.prescriptionType = prescriptionType;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public GetPrescriptionForExecutorUnsealedResultPart() {
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPrescriptionType(String prescriptionType) {
      this.prescriptionType = prescriptionType;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }
}
