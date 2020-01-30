package be.recipe.services.executor;

public class GetPrescriptionForExecutorSealedResultPart {
   private String patientId;
   private String prescriptionType;

   public void setPrescriptionType(String prescriptionType) {
      this.prescriptionType = prescriptionType;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }
}
