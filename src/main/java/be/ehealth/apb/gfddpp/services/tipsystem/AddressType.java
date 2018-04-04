package be.ehealth.apb.gfddpp.services.tipsystem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressType",
   namespace = "urn:be:fgov:ehealth:commons:core:v1",
   propOrder = {"street", "houseNumber", "postBox", "municipality", "country"}
)
public class AddressType {
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

   public void setStreet(Street var1) {
      this.street = var1;
   }

   public String getHouseNumber() {
      return this.houseNumber;
   }

   public void setHouseNumber(String var1) {
      this.houseNumber = var1;
   }

   public String getPostBox() {
      return this.postBox;
   }

   public void setPostBox(String var1) {
      this.postBox = var1;
   }

   public Municipality getMunicipality() {
      return this.municipality;
   }

   public void setMunicipality(Municipality var1) {
      this.municipality = var1;
   }

   public Country getCountry() {
      return this.country;
   }

   public void setCountry(Country var1) {
      this.country = var1;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"description"}
   )
   public static class Street {
      @XmlElement(
         name = "Description",
         required = true
      )
      protected List<LocalisedString> description;

      public List<LocalisedString> getDescription() {
         if (this.description == null) {
            this.description = new ArrayList();
         }

         return this.description;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"zipCode", "insCode", "description"}
   )
   public static class Municipality {
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
      protected List<LocalisedString> description;

      public String getZipCode() {
         return this.zipCode;
      }

      public void setZipCode(String var1) {
         this.zipCode = var1;
      }

      public String getInsCode() {
         return this.insCode;
      }

      public void setInsCode(String var1) {
         this.insCode = var1;
      }

      public List<LocalisedString> getDescription() {
         if (this.description == null) {
            this.description = new ArrayList();
         }

         return this.description;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"code", "abbreviation", "description"}
   )
   public static class Country {
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
      protected List<LocalisedString> description;

      public String getCode() {
         return this.code;
      }

      public void setCode(String var1) {
         this.code = var1;
      }

      public String getAbbreviation() {
         return this.abbreviation;
      }

      public void setAbbreviation(String var1) {
         this.abbreviation = var1;
      }

      public List<LocalisedString> getDescription() {
         if (this.description == null) {
            this.description = new ArrayList();
         }

         return this.description;
      }
   }
}
