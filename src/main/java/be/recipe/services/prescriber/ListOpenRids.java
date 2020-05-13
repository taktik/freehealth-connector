package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenRids",
   propOrder = {"listOpenRidsParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "listOpenRids"
)
public class ListOpenRids {
   @XmlElement(
      name = "ListOpenRidsParamSealed",
      required = true
   )
   protected byte[] listOpenRidsParamSealed;
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

   public byte[] getListOpenRidsParamSealed() {
      return this.listOpenRidsParamSealed;
   }

   public void setListOpenRidsParamSealed(byte[] value) {
      this.listOpenRidsParamSealed = value;
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
