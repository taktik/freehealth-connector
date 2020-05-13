package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dataSpecificParametersGetDataTypes",
   propOrder = {"startDate", "endDate"}
)
public class DataSpecificParametersGetDataTypes {
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar startDate;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar endDate;

   public XMLGregorianCalendar getStartDate() {
      return this.startDate;
   }

   public void setStartDate(XMLGregorianCalendar value) {
      this.startDate = value;
   }

   public XMLGregorianCalendar getEndDate() {
      return this.endDate;
   }

   public void setEndDate(XMLGregorianCalendar value) {
      this.endDate = value;
   }
}
