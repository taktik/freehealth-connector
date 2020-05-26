package be.fgov.ehealth.commons.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   protected AddressType.Street street;
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
   protected AddressType.Municipality municipality;
   @XmlElement(
      name = "Country"
   )
   protected AddressType.Country country;

   public AddressType.Street getStreet() {
      return this.street;
   }

   public void setStreet(AddressType.Street value) {
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

   public AddressType.Municipality getMunicipality() {
      return this.municipality;
   }

   public void setMunicipality(AddressType.Municipality value) {
      this.municipality = value;
   }

   public AddressType.Country getCountry() {
      return this.country;
   }

   public void setCountry(AddressType.Country value) {
      this.country = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"descriptions"}
   )
   public static class Street implements Serializable {
      private static final long serialVersionUID = 1L;
      @XmlElement(
         name = "Description",
         required = true
      )
      protected List<LocalisedString> descriptions;

      public List<LocalisedString> getDescriptions() {
         if (this.descriptions == null) {
            this.descriptions = new ArrayList();
         }

         return this.descriptions;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"zipCode", "insCode", "descriptions"}
   )
   public static class Municipality implements Serializable {
      private static final long serialVersionUID = 1L;
      @XmlElement(
         name = "ZipCode",
         required = true
      )
      protected String zipCode;
      @XmlElement(
         name = "InsCode"
      )
      protected String insCode;
      @XmlElement(
         name = "Description",
         required = true
      )
      protected List<LocalisedString> descriptions;

      public String getZipCode() {
         return this.zipCode;
      }

      public void setZipCode(String value) {
         this.zipCode = value;
      }

      public String getInsCode() {
         return this.insCode;
      }

      public void setInsCode(String value) {
         this.insCode = value;
      }

      public List<LocalisedString> getDescriptions() {
         if (this.descriptions == null) {
            this.descriptions = new ArrayList();
         }

         return this.descriptions;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"code", "abbreviation", "descriptions"}
   )
   public static class Country implements Serializable {
      private static final long serialVersionUID = 1L;
      @XmlElement(
         name = "Code"
      )
      protected String code;
      @XmlElement(
         name = "Abbreviation"
      )
      protected String abbreviation;
      @XmlElement(
         name = "Description"
      )
      protected List<LocalisedString> descriptions;

      public String getCode() {
         return this.code;
      }

      public void setCode(String value) {
         this.code = value;
      }

      public String getAbbreviation() {
         return this.abbreviation;
      }

      public void setAbbreviation(String value) {
         this.abbreviation = value;
      }

      public List<LocalisedString> getDescriptions() {
         if (this.descriptions == null) {
            this.descriptions = new ArrayList();
         }

         return this.descriptions;
      }
   }
}
