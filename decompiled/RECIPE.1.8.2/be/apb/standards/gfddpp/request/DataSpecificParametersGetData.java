package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dataSpecificParametersGetData",
   propOrder = {"excludeOwnData", "dateRangeType", "startDate", "endDate"}
)
public class DataSpecificParametersGetData {
   protected boolean excludeOwnData;
   @XmlElement(
      required = true
   )
   protected String dateRangeType;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar startDate;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar endDate;

   public boolean isExcludeOwnData() {
      return this.excludeOwnData;
   }

   public void setExcludeOwnData(boolean value) {
      this.excludeOwnData = value;
   }

   public String getDateRangeType() {
      return this.dateRangeType;
   }

   public void setDateRangeType(String value) {
      this.dateRangeType = value;
   }

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
