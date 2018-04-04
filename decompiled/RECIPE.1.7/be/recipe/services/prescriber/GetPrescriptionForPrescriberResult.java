package be.recipe.services.prescriber;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class GetPrescriptionForPrescriberResult {
   private String rid;
   private Calendar creationDate;
   private String patientId;
   private boolean feedbackAllowed = true;
   private byte[] prescription;
   private String encryptionKeyId;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public Calendar getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Calendar creationDate) {
      this.creationDate = creationDate;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public boolean getFeedbackAllowed() {
      return this.feedbackAllowed;
   }

   public void setFeedbackAllowed(boolean feedbackAllowed) {
      this.feedbackAllowed = feedbackAllowed;
   }

   public byte[] getPrescription() {
      return this.prescription;
   }

   public void setPrescription(byte[] prescription) {
      this.prescription = prescription;
   }

   public String getEncryptionKeyId() {
      return this.encryptionKeyId;
   }

   public void setEncryptionKeyId(String encryptionKeyId) {
      this.encryptionKeyId = encryptionKeyId;
   }
}
