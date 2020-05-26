package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionStatus",
   propOrder = {"getPrescriptionStatusParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "getPrescriptionStatus"
)
public class GetPrescriptionStatus {
   @XmlElement(
      name = "GetPrescriptionStatusParamSealed",
      required = true
   )
   protected byte[] getPrescriptionStatusParamSealed;
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

   public byte[] getGetPrescriptionStatusParamSealed() {
      return this.getPrescriptionStatusParamSealed;
   }

   public void setGetPrescriptionStatusParamSealed(byte[] value) {
      this.getPrescriptionStatusParamSealed = value;
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
