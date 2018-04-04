package be.behealth.webservices.tsa;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PeriodType",
   propOrder = {"start", "end"}
)
public class PeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Start"
   )
   protected long start;
   @XmlElement(
      name = "End"
   )
   protected long end;

   public long getStart() {
      return this.start;
   }

   public void setStart(long value) {
      this.start = value;
   }

   public long getEnd() {
      return this.end;
   }

   public void setEnd(long value) {
      this.end = value;
   }
}
