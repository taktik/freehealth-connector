package be.fgov.ehealth.rn.baselegaldata.v1;

import be.fgov.ehealth.rn.commons.business.v1.LocalizedDescriptionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LocationType",
   propOrder = {"countryCode", "countryIsoCode", "countryNames", "cityCode", "cityNames"}
)
public class LocationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer countryCode;
   @XmlElement(
      name = "CountryIsoCode"
   )
   protected String countryIsoCode;
   @XmlElement(
      name = "CountryName"
   )
   protected List<LocalizedDescriptionType> countryNames;
   @XmlElement(
      name = "CityCode"
   )
   protected String cityCode;
   @XmlElement(
      name = "CityName"
   )
   protected List<LocalizedDescriptionType> cityNames;

   public Integer getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(Integer value) {
      this.countryCode = value;
   }

   public String getCountryIsoCode() {
      return this.countryIsoCode;
   }

   public void setCountryIsoCode(String value) {
      this.countryIsoCode = value;
   }

   public List<LocalizedDescriptionType> getCountryNames() {
      if (this.countryNames == null) {
         this.countryNames = new ArrayList();
      }

      return this.countryNames;
   }

   public String getCityCode() {
      return this.cityCode;
   }

   public void setCityCode(String value) {
      this.cityCode = value;
   }

   public List<LocalizedDescriptionType> getCityNames() {
      if (this.cityNames == null) {
         this.cityNames = new ArrayList();
      }

      return this.cityNames;
   }
}
