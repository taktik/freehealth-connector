package be.fgov.ehealth.commons.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PeriodType",
   propOrder = {"beginDate", "endDate"}
)
public class PeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BeginDate",
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar beginDate;
   @XmlElement(
      name = "EndDate"
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar endDate;

   public XMLGregorianCalendar getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(XMLGregorianCalendar value) {
      this.beginDate = value;
   }

   public XMLGregorianCalendar getEndDate() {
      return this.endDate;
   }

   public void setEndDate(XMLGregorianCalendar value) {
      this.endDate = value;
   }
}
