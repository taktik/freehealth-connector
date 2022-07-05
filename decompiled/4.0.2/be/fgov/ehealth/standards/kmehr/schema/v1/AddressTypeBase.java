package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "addressTypeBase",
   propOrder = {"ids", "cds", "country", "zip", "nis", "city", "district", "street", "housenumber", "postboxnumber", "texts"}
)
@XmlSeeAlso({AddressType.class})
public class AddressTypeBase implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id"
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      name = "cd"
   )
   protected List<CDADDRESS> cds;
   protected CountryType country;
   protected String zip;
   protected String nis;
   protected String city;
   protected String district;
   protected String street;
   protected String housenumber;
   protected String postboxnumber;
   @XmlElement(
      name = "text"
   )
   protected List<TextType> texts;

   public AddressTypeBase() {
   }

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<CDADDRESS> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public CountryType getCountry() {
      return this.country;
   }

   public void setCountry(CountryType value) {
      this.country = value;
   }

   public String getZip() {
      return this.zip;
   }

   public void setZip(String value) {
      this.zip = value;
   }

   public String getNis() {
      return this.nis;
   }

   public void setNis(String value) {
      this.nis = value;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String value) {
      this.city = value;
   }

   public String getDistrict() {
      return this.district;
   }

   public void setDistrict(String value) {
      this.district = value;
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

   public List<TextType> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }
}
