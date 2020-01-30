package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import org.taktik.connector.business.recipe.utils.CalendarAdapter;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionForPrescriberResult",
   propOrder = {"creationDate", "encryptionKeyId", "feedbackAllowed", "patientId", "prescription", "rid", "expirationDate"}
)
@XmlRootElement(
   name = "getPrescriptionForPrescriberResult"
)
public class GetPrescriptionForPrescriberResult extends ResponseType {
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar creationDate;
   protected String encryptionKeyId;
   protected boolean feedbackAllowed;
   protected String patientId;
   protected byte[] prescription;
   protected String rid;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar expirationDate;

   public Calendar getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Calendar value) {
      this.creationDate = value;
   }

   public String getEncryptionKeyId() {
      return this.encryptionKeyId;
   }

   public void setEncryptionKeyId(String value) {
      this.encryptionKeyId = value;
   }

   public boolean isFeedbackAllowed() {
      return this.feedbackAllowed;
   }

   public void setFeedbackAllowed(boolean value) {
      this.feedbackAllowed = value;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
   }

   public byte[] getPrescription() {
      return this.prescription;
   }

   public void setPrescription(byte[] value) {
      this.prescription = value;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public Calendar getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(Calendar value) {
      this.expirationDate = value;
   }

}
