package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "revokePrescription",
   propOrder = {"revokePrescriptionParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "revokePrescription"
)
public class RevokePrescription {
   @XmlElement(
      name = "RevokePrescriptionParamSealed",
      required = true
   )
   protected byte[] revokePrescriptionParamSealed;
   @XmlElement(
      name = "ProgramIdentification",
      required = true
   )
   protected String programIdentification;
   @XmlElement(
      name = "Mguid",
      required = true
   )
   protected String mguid;

   public byte[] getRevokePrescriptionParamSealed() {
      return this.revokePrescriptionParamSealed;
   }

   public void setRevokePrescriptionParamSealed(byte[] value) {
      this.revokePrescriptionParamSealed = value;
   }

   public String getProgramIdentification() {
      return this.programIdentification;
   }

   public void setProgramIdentification(String value) {
      this.programIdentification = value;
   }

   public String getMguid() {
      return this.mguid;
   }

   public void setMguid(String value) {
      this.mguid = value;
   }
}
