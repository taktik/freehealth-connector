package be.apb.standards.gfddpp.request.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"version", "nihiiPharmacyNumber"}
)
@XmlRootElement(
   name = "getConfigurationRequestParameters"
)
public class GetConfigurationRequestParameters {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar version;
   @XmlElement(
      required = true
   )
   protected String nihiiPharmacyNumber;

   public XMLGregorianCalendar getVersion() {
      return this.version;
   }

   public void setVersion(XMLGregorianCalendar value) {
      this.version = value;
   }

   public String getNihiiPharmacyNumber() {
      return this.nihiiPharmacyNumber;
   }

   public void setNihiiPharmacyNumber(String value) {
      this.nihiiPharmacyNumber = value;
   }
}
