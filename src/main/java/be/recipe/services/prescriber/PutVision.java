package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "putVision",
   propOrder = {"putVisionParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "putVision"
)
public class PutVision {
   @XmlElement(
      name = "PutVisionParamSealed",
      required = true
   )
   protected byte[] putVisionParamSealed;
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

   public byte[] getPutVisionParamSealed() {
      return this.putVisionParamSealed;
   }

   public void setPutVisionParamSealed(byte[] value) {
      this.putVisionParamSealed = value;
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
