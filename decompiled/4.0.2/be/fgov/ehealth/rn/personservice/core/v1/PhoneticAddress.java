package be.fgov.ehealth.rn.personservice.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticAddress",
   propOrder = {"countryCode", "cityCode"}
)
public class PhoneticAddress implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected int countryCode;
   @XmlElement(
      name = "CityCode"
   )
   protected String cityCode;

   public PhoneticAddress() {
   }

   public int getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(int value) {
      this.countryCode = value;
   }

   public String getCityCode() {
      return this.cityCode;
   }

   public void setCityCode(String value) {
      this.cityCode = value;
   }
}
