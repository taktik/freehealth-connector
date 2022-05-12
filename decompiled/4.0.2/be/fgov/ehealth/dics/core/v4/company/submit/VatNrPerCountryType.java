package be.fgov.ehealth.dics.core.v4.company.submit;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VatNrPerCountryType",
   propOrder = {"value"}
)
public class VatNrPerCountryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "CountryCode",
      required = true
   )
   protected String countryCode;

   public VatNrPerCountryType() {
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(String value) {
      this.countryCode = value;
   }
}
