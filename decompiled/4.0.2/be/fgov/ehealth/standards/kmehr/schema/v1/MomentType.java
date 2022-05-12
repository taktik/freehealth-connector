package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import be.ehealth.technicalconnector.adapter.XmlTimeAdapter;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "momentType",
   propOrder = {"text", "date", "time", "yearmonth", "year"}
)
@XmlSeeAlso({DateType.class})
public class MomentType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected TextType text;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlTimeAdapter.class)
   @XmlSchemaType(
      name = "time"
   )
   protected DateTime time;
   @XmlSchemaType(
      name = "gYearMonth"
   )
   protected XMLGregorianCalendar yearmonth;
   @XmlSchemaType(
      name = "gYear"
   )
   protected XMLGregorianCalendar year;

   public MomentType() {
   }

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public DateTime getTime() {
      return this.time;
   }

   public void setTime(DateTime value) {
      this.time = value;
   }

   public XMLGregorianCalendar getYearmonth() {
      return this.yearmonth;
   }

   public void setYearmonth(XMLGregorianCalendar value) {
      this.yearmonth = value;
   }

   public XMLGregorianCalendar getYear() {
      return this.year;
   }

   public void setYear(XMLGregorianCalendar value) {
      this.year = value;
   }
}
