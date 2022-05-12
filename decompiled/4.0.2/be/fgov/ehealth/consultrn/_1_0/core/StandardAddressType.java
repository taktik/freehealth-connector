package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StandardAddressType",
   propOrder = {"street", "housenumber", "box", "municipality", "country"}
)
public class StandardAddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Street"
   )
   protected StreetType street;
   @XmlElement(
      name = "Housenumber"
   )
   protected String housenumber;
   @XmlElement(
      name = "Box"
   )
   protected String box;
   @XmlElement(
      name = "Municipality"
   )
   protected MunicipalityType municipality;
   @XmlElement(
      name = "Country",
      required = true
   )
   protected CountryType country;

   public StandardAddressType() {
   }

   public StreetType getStreet() {
      return this.street;
   }

   public void setStreet(StreetType value) {
      this.street = value;
   }

   public String getHousenumber() {
      return this.housenumber;
   }

   public void setHousenumber(String value) {
      this.housenumber = value;
   }

   public String getBox() {
      return this.box;
   }

   public void setBox(String value) {
      this.box = value;
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
