package be.cin.mycarenet._1_0.carenet.types;

import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PeriodLengthType",
   propOrder = {"periodStart", "periodLength", "periodEnd"}
)
public class PeriodLengthType implements Serializable {
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
      name = "PeriodLength"
   )
   protected PeriodLength periodLength;
   @XmlElement(
      name = "PeriodEnd",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime periodEnd;

   public DateTime getPeriodStart() {
      return this.periodStart;
   }

   public void setPeriodStart(DateTime value) {
      this.periodStart = value;
   }

   public PeriodLength getPeriodLength() {
      return this.periodLength;
   }

   public void setPeriodLength(PeriodLength value) {
      this.periodLength = value;
   }

   public DateTime getPeriodEnd() {
      return this.periodEnd;
   }

   public void setPeriodEnd(DateTime value) {
      this.periodEnd = value;
   }
}
