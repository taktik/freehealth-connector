package be.cin.types.v1;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PeriodType",
   propOrder = {"start", "end"}
)
public class PeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Start",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime start;
   @XmlElement(
      name = "End",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime end;

   public DateTime getStart() {
      return this.start;
   }

   public void setStart(DateTime value) {
      this.start = value;
   }

   public DateTime getEnd() {
      return this.end;
   }

   public void setEnd(DateTime value) {
      this.end = value;
   }
}
