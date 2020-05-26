package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDDAYPERIOD;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DayPeriodCodeType",
   propOrder = {"cd"}
)
public class DayPeriodCodeType extends AbstractDayPeriodCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDDAYPERIOD cd;

   public CDDAYPERIOD getCd() {
      return this.cd;
   }

   public void setCd(CDDAYPERIOD value) {
      this.cd = value;
   }
}
