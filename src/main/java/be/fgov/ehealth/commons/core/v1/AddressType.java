package be.fgov.ehealth.commons.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressType",
   propOrder = {"street", "houseNumber", "postBox", "municipality", "country"}
)
public class AddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Street",
      required = true
   )
   protected Street street;
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

   public Street getStreet() {
      return this.street;
   }

   public void setStreet(Street value) {
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
}
