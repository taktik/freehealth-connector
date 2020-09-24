package be.fgov.ehealth.commons.core.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CountryType",
   propOrder = {"code", "abbreviation", "descriptions"}
)
public class CountryType {
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
