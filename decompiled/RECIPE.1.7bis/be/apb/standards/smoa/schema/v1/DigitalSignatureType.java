package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DigitalSignatureType",
   propOrder = {"dateTime", "signature"}
)
public class DigitalSignatureType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar dateTime;
   @XmlElement(
      required = true
   )
   protected String signature;

   public XMLGregorianCalendar getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(XMLGregorianCalendar value) {
      this.dateTime = value;
   }

   public String getSignature() {
      return this.signature;
   }

   public void setSignature(String value) {
      this.signature = value;
   }
}
