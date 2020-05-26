package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionForPrescriber",
   propOrder = {"getPrescriptionForPrescriberParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "getPrescriptionForPrescriber"
)
public class GetPrescriptionForPrescriber {
   @XmlElement(
      name = "GetPrescriptionForPrescriberParamSealed",
      required = true
   )
   protected byte[] getPrescriptionForPrescriberParamSealed;
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

   public byte[] getGetPrescriptionForPrescriberParamSealed() {
      return this.getPrescriptionForPrescriberParamSealed;
   }

   public void setGetPrescriptionForPrescriberParamSealed(byte[] value) {
      this.getPrescriptionForPrescriberParamSealed = value;
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
