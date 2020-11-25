package be.fgov.ehealth.insurability.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CoverageType",
   propOrder = {"communicated", "period", "entitlement"}
)
public class CoverageType {
   @XmlElement(
      name = "Communicated",
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar communicated;
   @XmlElement(
      name = "Period",
      required = true
   )
   protected PeriodType period;
   @XmlElement(
      name = "Entitlement",
      required = true
   )
   protected EntitlementType entitlement;

   public XMLGregorianCalendar getCommunicated() {
      return this.communicated;
   }

   public void setCommunicated(XMLGregorianCalendar value) {
      this.communicated = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }

   public EntitlementType getEntitlement() {
      return this.entitlement;
   }

   public void setEntitlement(EntitlementType value) {
      this.entitlement = value;
   }
}
