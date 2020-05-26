package be.recipe.services.prescriber;

import be.recipe.services.PartyIdentification;
import java.util.Arrays;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class CreatePrescriptionParam extends PartyIdentification {
   private byte[] prescription;
   @NotNull
   @Size(
      max = 2
   )
   private String prescriptionType;
   @NotNull
   private Boolean feedbackRequested;
   @NotNull
   private String keyId;
   @NotNull
   private byte[] symmKey;
   private String prescriberLabel;

   @NotNull
   public String getPatientId() {
      return super.getPatientId();
   }

   @NotNull
   public String getPrescriberId() {
      return super.getPrescriberId();
   }

   public CreatePrescriptionParam() {
   }

   public CreatePrescriptionParam(String patientId, byte[] prescription, boolean feedbackRequested, byte[] symmKey) {
      this.setPatientId(patientId);
      this.prescription = prescription;
      this.feedbackRequested = feedbackRequested;
      this.symmKey = symmKey;
   }

   public boolean isFeedbackRequested() {
      return this.feedbackRequested;
   }

   public void setFeedbackRequested(boolean feedbackRequested) {
      this.feedbackRequested = feedbackRequested;
   }

   public byte[] getPrescription() {
      return this.prescription;
   }

   public void setPrescription(byte[] prescription) {
      this.prescription = prescription;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String prescriptionType) {
      this.prescriptionType = prescriptionType;
   }

   public void setFeedbackRequested(Boolean feedbackRequested) {
      this.feedbackRequested = feedbackRequested;
   }

   public String getKeyId() {
      return this.keyId;
   }

   public void setKeyId(String keyId) {
      this.keyId = keyId;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public String toString() {
      return "CreatePrescriptionParam [etk=" + Arrays.toString(this.symmKey) + ", feedbackRequested=" + this.feedbackRequested + ", keyId=" + this.keyId + ", prescription=" + Arrays.toString(this.prescription) + ", prescriptionType=" + this.prescriptionType + "]";
   }

   public String getPrescriberLabel() {
      return this.prescriberLabel;
   }

   public void setPrescriberLabel(String label) {
      this.prescriberLabel = label;
   }
}
