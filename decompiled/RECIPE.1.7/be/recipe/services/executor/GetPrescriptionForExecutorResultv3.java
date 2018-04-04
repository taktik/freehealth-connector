package be.recipe.services.executor;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class GetPrescriptionForExecutorResultv3 {
   private String rid;
   private Calendar creationDate;
   private String patientId;
   private boolean feedbackAllowed = true;
   private byte[] prescription;
   private String prescriptionType;
   private String encryptionKeyId;
   private String timestampingId;
   private String prescriberId;

   public String getPrescriberId() {
      return this.prescriberId;
   }

   public void setPrescriberId(String prescriberId) {
      this.prescriberId = prescriberId;
   }

   public Calendar getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Calendar creationDate) {
      this.creationDate = creationDate;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String prescriptionType) {
      this.prescriptionType = prescriptionType;
   }

   public void setFeedbackAllowed(boolean feedbackAllowed) {
      this.feedbackAllowed = feedbackAllowed;
   }

   public boolean isFeedbackAllowed() {
      return this.feedbackAllowed;
   }

   /** @deprecated */
   @Deprecated
   public void setFeedbackRequested(boolean feedbackAllowed) {
      this.feedbackAllowed = feedbackAllowed;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
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

   public String getTimestampingId() {
      return this.timestampingId;
   }

   public void setTimestampingId(String timestampingId) {
      this.timestampingId = timestampingId;
   }
}
