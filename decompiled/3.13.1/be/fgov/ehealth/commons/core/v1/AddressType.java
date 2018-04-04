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
   protected MunicipalityType municipality;
   @XmlElement(
      name = "Country"
   )
   protected CountryType country;

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

   public MunicipalityType getMunicipality() {
      return this.municipality;
   }

   public void setMunicipality(MunicipalityType value) {
      this.municipality = value;
   }

   public CountryType getCountry() {
      return this.country;
   }

   public void setCountry(CountryType value) {
      this.country = value;
   }
}
