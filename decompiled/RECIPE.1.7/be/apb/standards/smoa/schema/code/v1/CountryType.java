package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDCOUNTRY;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CountryType",
   propOrder = {"code"}
)
public class CountryType extends AbstractCountryCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDCOUNTRY code;

   public CDCOUNTRY getCode() {
      return this.code;
   }

   public void setCode(CDCOUNTRY value) {
      this.code = value;
   }
}
