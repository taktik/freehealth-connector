package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.CDADDRESS;
import be.apb.standards.smoa.schema.v1.CDCOUNTRY;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EuropeanAddressType",
   propOrder = {"country", "zip", "city", "street", "housenumber", "postboxnumber", "lastChanged"}
)
public class EuropeanAddressType extends AbstractAddressType {
   @XmlSchemaType(
      name = "token"
   )
   protected CDCOUNTRY country;
   @XmlElement(
      required = true
   )
   protected String zip;
   protected String city;
   @XmlElement(
      required = true
   )
   protected String street;
   @XmlElement(
      required = true
   )
   protected String housenumber;
   protected String postboxnumber;
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar lastChanged;
   @XmlAttribute(
      name = "usage",
      required = true
   )
   protected CDADDRESS usage;

   public CDCOUNTRY getCountry() {
      return this.country;
   }

   public void setCountry(CDCOUNTRY value) {
      this.country = value;
   }

   public String getZip() {
      return this.zip;
   }

   public void setZip(String value) {
      this.zip = value;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String value) {
      this.city = value;
   }

   public String getStreet() {
      return this.street;
   }

   public void setStreet(String value) {
      this.street = value;
   }

   public String getHousenumber() {
      return this.housenumber;
   }

   public void setHousenumber(String value) {
      this.housenumber = value;
   }

   public String getPostboxnumber() {
      return this.postboxnumber;
   }

   public void setPostboxnumber(String value) {
      this.postboxnumber = value;
   }

   public XMLGregorianCalendar getLastChanged() {
      return this.lastChanged;
   }

   public void setLastChanged(XMLGregorianCalendar value) {
      this.lastChanged = value;
   }

   public CDADDRESS getUsage() {
      return this.usage;
   }

   public void setUsage(CDADDRESS value) {
      this.usage = value;
   }
}
