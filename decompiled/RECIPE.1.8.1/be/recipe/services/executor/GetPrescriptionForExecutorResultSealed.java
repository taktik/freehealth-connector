package be.recipe.services.executor;

import be.recipe.common.util.Utils;
import be.recipe.services.core.ResponseType;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class GetPrescriptionForExecutorResultSealed extends ResponseType {
   private String rid;
   private Calendar creationDate;
   private String patientId;
   private boolean feedbackAllowed = true;
   private byte[] prescription;
   private String prescriptionType;
   private String encryptionKeyId;
   private String timestampingId;
   private String prescriberId;
   private Calendar expirationDate;

   public GetPrescriptionForExecutorResultSealed() {
   }

   GetPrescriptionForExecutorResultSealed(String rid, Date creationDate, String patientId, boolean feedbackAllowed, boolean feedbackAllowedByPatient, byte[] prescription, String encryptionKeyId, String prescriptionType, String timestampingId, String prescriberId) {
      this.rid = rid;
      this.creationDate = new GregorianCalendar();
      this.creationDate.setTime(creationDate);
      this.patientId = Utils.formatId(patientId, 11);
      this.feedbackAllowed = feedbackAllowed && feedbackAllowedByPatient;
      this.prescription = prescription;
      this.encryptionKeyId = encryptionKeyId;
      this.prescriptionType = prescriptionType;
      this.timestampingId = timestampingId;
      this.prescriberId = prescriberId;
   }

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

   public Calendar getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(Calendar expirationDate) {
      this.expirationDate = expirationDate;
   }
}
