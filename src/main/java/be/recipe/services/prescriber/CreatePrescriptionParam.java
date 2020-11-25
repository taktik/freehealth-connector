package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "createPrescriptionParam",
   propOrder = {"prescription", "prescriptionType", "feedbackRequested", "keyId", "symmKey", "prescriberLabel", "expirationDate", "vision", "prescriberId"}
)
@XmlRootElement(
   name = "createPrescriptionParam"
)
public class CreatePrescriptionParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected byte[] prescription;
   @XmlElement(
      required = true
   )
   protected String prescriptionType;
   protected boolean feedbackRequested;
   @XmlElement(
      required = true
   )
   protected String keyId;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   @XmlElement(
      required = true
   )
   protected String prescriberLabel;
   @XmlElement(
      required = true
   )
   protected String expirationDate;
   @XmlElement(
      required = true
   )
   protected String vision;

   protected String prescriberId;

   public byte[] getPrescription() {
      return this.prescription;
   }

   public void setPrescription(byte[] value) {
      this.prescription = value;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
   }

   public boolean isFeedbackRequested() {
      return this.feedbackRequested;
   }

   public void setFeedbackRequested(boolean value) {
      this.feedbackRequested = value;
   }

   public String getKeyId() {
      return this.keyId;
   }

   public void setKeyId(String value) {
      this.keyId = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public String getPrescriberLabel() {
      return this.prescriberLabel;
   }

   public void setPrescriberLabel(String value) {
      this.prescriberLabel = value;
   }

   public String getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(String value) {
      this.expirationDate = value;
   }

   public String getVision() {
      return this.vision;
   }

   public void setVision(String value) {
      this.vision = value;
   }

   public String getPrescriberId() {
      return prescriberId;
   }

   public void setPrescriberId(String prescriberId) {
      this.prescriberId = prescriberId;
   }
}
