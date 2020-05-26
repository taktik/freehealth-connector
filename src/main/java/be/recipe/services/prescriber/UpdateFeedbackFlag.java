package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "updateFeedbackFlag",
   propOrder = {"updateFeedbackFlagParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "updateFeedbackFlag"
)
public class UpdateFeedbackFlag {
   @XmlElement(
      name = "UpdateFeedbackFlagParamSealed",
      required = true
   )
   protected byte[] updateFeedbackFlagParamSealed;
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

   public byte[] getUpdateFeedbackFlagParamSealed() {
      return this.updateFeedbackFlagParamSealed;
   }

   public void setUpdateFeedbackFlagParamSealed(byte[] value) {
      this.updateFeedbackFlagParamSealed = value;
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
