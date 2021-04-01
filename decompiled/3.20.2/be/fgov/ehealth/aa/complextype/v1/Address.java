package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressType",
   propOrder = {"bstCode", "street", "houseNumber", "postBox", "municipality", "country"}
)
@XmlRootElement(
   name = "Address"
)
public class Address implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BSTCode"
   )
   protected String bstCode;
   @XmlElement(
      name = "Street",
      required = true
   )
   protected StreetType street;
   @XmlElement(
      name = "HouseNumber"
   )
   protected String houseNumber;
   @XmlElement(
      name = "PostBox"
   )
   protected String postBox;
   @XmlElement(
      name = "Municipality",
      required = true
   )
   protected Municipality municipality;
   @XmlElement(
      name = "Country"
   )
   protected Country country;
   @XmlAttribute(
      name = "Type"
   )
   protected String type;

   public String getBSTCode() {
      return this.bstCode;
   }

   public void setBSTCode(String value) {
      this.bstCode = value;
   }

   public StreetType getStreet() {
      return this.street;
   }

   public void setStreet(StreetType value) {
      this.street = value;
   }

   public String getHouseNumber() {
      return this.houseNumber;
   }

   public void setHouseNumber(String value) {
      this.houseNumber = value;
   }

   public String getPostBox() {
      return this.postBox;
   }

   public void setPostBox(String value) {
      this.postBox = value;
   }

   public Municipality getMunicipality() {
      return this.municipality;
   }

   public void setMunicipality(Municipality value) {
      this.municipality = value;
   }

   public Country getCountry() {
      return this.country;
   }

   public void setCountry(Country value) {
      this.country = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }
}
