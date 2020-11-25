package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractDayPeriodCodeType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegimenDayTimeType",
   propOrder = {"time", "dayperiod"}
)
public class RegimenDayTimeType {
   @XmlSchemaType(
      name = "time"
   )
   protected XMLGregorianCalendar time;
   protected AbstractDayPeriodCodeType dayperiod;

   public XMLGregorianCalendar getTime() {
      return this.time;
   }

   public void setTime(XMLGregorianCalendar value) {
      this.time = value;
   }

   public AbstractDayPeriodCodeType getDayperiod() {
      return this.dayperiod;
   }

   public void setDayperiod(AbstractDayPeriodCodeType value) {
      this.dayperiod = value;
   }
}
