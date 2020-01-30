package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BvacPatient",
   propOrder = {"identification", "identity", "birthDate", "nhiData"}
)
public class BvacPatient {
   protected IdentificationBvacPatient identification;
   @XmlElement(
      required = true
   )
   protected IdentityBvac identity;
   @XmlElement(
      defaultValue = "0001-01-01"
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar birthDate;
   protected NhiData nhiData;

   public IdentificationBvacPatient getIdentification() {
      return this.identification;
   }

   public void setIdentification(IdentificationBvacPatient value) {
      this.identification = value;
   }

   public IdentityBvac getIdentity() {
      return this.identity;
   }

   public void setIdentity(IdentityBvac value) {
      this.identity = value;
   }

   public XMLGregorianCalendar getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(XMLGregorianCalendar value) {
      this.birthDate = value;
   }

   public NhiData getNhiData() {
      return this.nhiData;
   }

   public void setNhiData(NhiData value) {
      this.nhiData = value;
   }
}
