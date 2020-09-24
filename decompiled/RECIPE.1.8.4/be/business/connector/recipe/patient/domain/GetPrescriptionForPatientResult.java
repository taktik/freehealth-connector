package be.business.connector.recipe.patient.domain;

import be.recipe.services.core.ResponseType;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForPatientResult"
)
public class GetPrescriptionForPatientResult extends ResponseType {
   private String rid;
   private Calendar creationDate;
   private String prescriberId;
   private boolean feedbackAllowed = true;
   private byte[] prescription;
   private String prescriptionType;
   private String encryptionKeyId;

   public GetPrescriptionForPatientResult() {
   }

   public GetPrescriptionForPatientResult(byte[] prescription) {
      this.prescription = prescription;
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

   public String getPrescriberId() {
      return this.prescriberId;
   }

   public void setPrescriberId(String prescriberId) {
      this.prescriberId = prescriberId;
   }

   public boolean isFeedbackAllowed() {
      return this.feedbackAllowed;
   }

   /** @deprecated */
   @Deprecated
   public void setFeedbackRequested(boolean feedbackAllowed) {
      this.feedbackAllowed = feedbackAllowed;
   }

   public byte[] getPrescription() {
      return (byte[])this.prescription.clone();
   }

   public void setPrescription(byte[] prescription) {
      this.prescription = (byte[])prescription.clone();
   }

   public String getEncryptionKeyId() {
      return this.encryptionKeyId;
   }

   public void setEncryptionKeyId(String encryptionKeyId) {
      this.encryptionKeyId = encryptionKeyId;
   }
}
