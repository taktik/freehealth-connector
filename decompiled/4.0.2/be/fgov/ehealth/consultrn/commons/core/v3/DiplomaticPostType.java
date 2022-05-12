package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DiplomaticPostType",
   propOrder = {"countryCode", "countryNames", "diplomaticPostCode", "diplomaticPostNames"}
)
public class DiplomaticPostType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryCode"
   )
   protected String countryCode;
   @XmlElement(
      name = "CountryName"
   )
   protected List<NameType> countryNames;
   @XmlElement(
      name = "DiplomaticPostCode"
   )
   protected String diplomaticPostCode;
   @XmlElement(
      name = "DiplomaticPostName"
   )
   protected List<NameType> diplomaticPostNames;

   public DiplomaticPostType() {
   }

   public String getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(String value) {
      this.countryCode = value;
   }

   public List<NameType> getCountryNames() {
      if (this.countryNames == null) {
         this.countryNames = new ArrayList();
      }

      return this.countryNames;
   }

   public String getDiplomaticPostCode() {
      return this.diplomaticPostCode;
   }

   public void setDiplomaticPostCode(String value) {
      this.diplomaticPostCode = value;
   }

   public List<NameType> getDiplomaticPostNames() {
      if (this.diplomaticPostNames == null) {
         this.diplomaticPostNames = new ArrayList();
      }

      return this.diplomaticPostNames;
   }
}
