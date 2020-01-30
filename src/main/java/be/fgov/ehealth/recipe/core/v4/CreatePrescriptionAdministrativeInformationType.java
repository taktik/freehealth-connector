package be.fgov.ehealth.recipe.core.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreatePrescriptionAdministrativeInformationType",
   propOrder = {"prescriptionType", "prescriptionVersion", "referenceSourceVersion", "keyIdentifier"}
)
public class CreatePrescriptionAdministrativeInformationType {
   @XmlElement(
      name = "PrescriptionType",
      required = true
   )
   protected String prescriptionType;
   @XmlElement(
      name = "PrescriptionVersion",
      required = true
   )
   protected String prescriptionVersion;
   @XmlElement(
      name = "ReferenceSourceVersion",
      required = true
   )
   protected String referenceSourceVersion;
   @XmlElement(
      name = "KeyIdentifier",
      required = true
   )
   protected byte[] keyIdentifier;

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
   }

   public String getPrescriptionVersion() {
      return this.prescriptionVersion;
   }

   public void setPrescriptionVersion(String value) {
      this.prescriptionVersion = value;
   }

   public String getReferenceSourceVersion() {
      return this.referenceSourceVersion;
   }

   public void setReferenceSourceVersion(String value) {
      this.referenceSourceVersion = value;
   }

   public byte[] getKeyIdentifier() {
      return this.keyIdentifier;
   }

   public void setKeyIdentifier(byte[] value) {
      this.keyIdentifier = (byte[])value;
   }
}
