package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listRidsHistory",
   propOrder = {"listRidsHistoryParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "listRidsHistory"
)
public class ListRidsHistory {
   @XmlElement(
      name = "ListRidsHistoryParamSealed",
      required = true
   )
   protected byte[] listRidsHistoryParamSealed;
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

   public byte[] getListRidsHistoryParamSealed() {
      return this.listRidsHistoryParamSealed;
   }

   public void setListRidsHistoryParamSealed(byte[] value) {
      this.listRidsHistoryParamSealed = value;
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
