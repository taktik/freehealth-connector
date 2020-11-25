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
   name = "DiplomaticPostType",
   propOrder = {"countryCode", "countryIsoCode", "countryNames", "diplomaticPostCode", "diplomaticPostNames"}
)
public class DiplomaticPostType implements Serializable {
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
      name = "DiplomaticPostCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer diplomaticPostCode;
   @XmlElement(
      name = "DiplomaticPostName"
   )
   protected List<LocalizedDescriptionType> diplomaticPostNames;

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

   public Integer getDiplomaticPostCode() {
      return this.diplomaticPostCode;
   }

   public void setDiplomaticPostCode(Integer value) {
      this.diplomaticPostCode = value;
   }

   public List<LocalizedDescriptionType> getDiplomaticPostNames() {
      if (this.diplomaticPostNames == null) {
         this.diplomaticPostNames = new ArrayList();
      }

      return this.diplomaticPostNames;
   }
}
