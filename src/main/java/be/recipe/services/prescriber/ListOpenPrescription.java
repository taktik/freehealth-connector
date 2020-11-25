package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenPrescription",
   propOrder = {"getListOpenPrescriptionParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "listOpenPrescription"
)
public class ListOpenPrescription {
   @XmlElement(
      name = "GetListOpenPrescriptionParamSealed",
      required = true
   )
   protected byte[] getListOpenPrescriptionParamSealed;
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

   public byte[] getGetListOpenPrescriptionParamSealed() {
      return this.getListOpenPrescriptionParamSealed;
   }

   public void setGetListOpenPrescriptionParamSealed(byte[] value) {
      this.getListOpenPrescriptionParamSealed = value;
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
