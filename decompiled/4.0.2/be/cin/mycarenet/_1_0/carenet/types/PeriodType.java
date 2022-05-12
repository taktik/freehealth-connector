package be.cin.mycarenet._1_0.carenet.types;

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
   propOrder = {"periodStart", "periodEnd"}
)
public class PeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PeriodStart",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime periodStart;
   @XmlElement(
      name = "PeriodEnd",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime periodEnd;

   public PeriodType() {
   }

   public DateTime getPeriodStart() {
      return this.periodStart;
   }

   public void setPeriodStart(DateTime value) {
      this.periodStart = value;
   }

   public DateTime getPeriodEnd() {
      return this.periodEnd;
   }

   public void setPeriodEnd(DateTime value) {
      this.periodEnd = value;
   }
}
