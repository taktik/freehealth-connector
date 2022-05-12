package be.fgov.ehealth.rn.baselegaldata.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.rn.commons.business.v1.LocalizedDescriptionType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ForeignAddressDeclarationType",
   propOrder = {"countryIsoCode", "countryCode", "countryName", "cityName", "postalCode", "streetName", "houseNumber", "boxNumber", "inceptionDate"}
)
public class ForeignAddressDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryIsoCode"
   )
   protected String countryIsoCode;
   @XmlElement(
      name = "CountryCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer countryCode;
   @XmlElement(
      name = "CountryName"
   )
   protected LocalizedDescriptionType countryName;
   @XmlElement(
      name = "CityName"
   )
   protected LocalizedDescriptionType cityName;
   @XmlElement(
      name = "PostalCode"
   )
   protected String postalCode;
   @XmlElement(
      name = "StreetName"
   )
   protected LocalizedDescriptionType streetName;
   @XmlElement(
      name = "HouseNumber"
   )
   protected String houseNumber;
   @XmlElement(
      name = "BoxNumber"
   )
   protected String boxNumber;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public ForeignAddressDeclarationType() {
   }

   public String getCountryIsoCode() {
      return this.countryIsoCode;
   }

   public void setCountryIsoCode(String value) {
      this.countryIsoCode = value;
   }

   public Integer getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(Integer value) {
      this.countryCode = value;
   }

   public LocalizedDescriptionType getCountryName() {
      return this.countryName;
   }

   public void setCountryName(LocalizedDescriptionType value) {
      this.countryName = value;
   }

   public LocalizedDescriptionType getCityName() {
      return this.cityName;
   }

   public void setCityName(LocalizedDescriptionType value) {
      this.cityName = value;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String value) {
      this.postalCode = value;
   }

   public LocalizedDescriptionType getStreetName() {
      return this.streetName;
   }

   public void setStreetName(LocalizedDescriptionType value) {
      this.streetName = value;
   }

   public String getHouseNumber() {
      return this.houseNumber;
   }

   public void setHouseNumber(String value) {
      this.houseNumber = value;
   }

   public String getBoxNumber() {
      return this.boxNumber;
   }

   public void setBoxNumber(String value) {
      this.boxNumber = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
