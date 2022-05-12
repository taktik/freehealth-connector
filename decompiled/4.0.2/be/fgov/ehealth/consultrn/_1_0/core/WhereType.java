package be.fgov.ehealth.consultrn._1_0.core;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "WhereType",
   propOrder = {"descriptions", "municipality", "country"}
)
public class WhereType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;
   @XmlElement(
      name = "Municipality"
   )
   protected MunicipalityType municipality;
   @XmlElement(
      name = "Country"
   )
   protected CountryType country;

   public WhereType() {
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
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
