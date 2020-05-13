package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PeriodType",
   namespace = "urn:be:fgov:ehealth:commons:core:v1",
   propOrder = {"beginDate", "endDate"}
)
public class PeriodType {
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

   public void setBeginDate(XMLGregorianCalendar var1) {
      this.beginDate = var1;
   }

   public XMLGregorianCalendar getEndDate() {
      return this.endDate;
   }

   public void setEndDate(XMLGregorianCalendar var1) {
      this.endDate = var1;
   }
}
