package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacks",
   propOrder = {"listFeedbacksParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "listFeedbacks"
)
public class ListFeedbacks {
   @XmlElement(
      name = "ListFeedbacksParamSealed",
      required = true
   )
   protected byte[] listFeedbacksParamSealed;
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

   public byte[] getListFeedbacksParamSealed() {
      return this.listFeedbacksParamSealed;
   }

   public void setListFeedbacksParamSealed(byte[] value) {
      this.listFeedbacksParamSealed = value;
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
