package be.fgov.ehealth.recipe.core.v1;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreatePrescriptionAdministrativeInformationType",
   propOrder = {"prescriberIdentifier", "prescriptionType", "keyIdentifier"}
)
public class CreatePrescriptionAdministrativeInformationType extends PrescriberServiceAdministrativeInformationType {
   @XmlElement(
      name = "PrescriberIdentifier",
      required = true
   )
   protected IdentifierType prescriberIdentifier;
   @XmlElement(
      name = "PrescriptionType",
      required = true
   )
   protected String prescriptionType;
   @XmlElement(
      name = "KeyIdentifier",
      required = true
   )
   protected byte[] keyIdentifier;

   public IdentifierType getPrescriberIdentifier() {
      return this.prescriberIdentifier;
   }

   public void setPrescriberIdentifier(IdentifierType value) {
      this.prescriberIdentifier = value;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
   }

   public byte[] getKeyIdentifier() {
      return this.keyIdentifier;
   }

   public void setKeyIdentifier(byte[] value) {
      this.keyIdentifier = (byte[])value;
   }
}
