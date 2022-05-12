package be.fgov.ehealth.mediprima.core.v2;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
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
   name = "BySsinType",
   propOrder = {"ssin", "referenceDate", "period"}
)
public class BySsinType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin",
      required = true
   )
   protected String ssin;
   @XmlElement(
      name = "ReferenceDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime referenceDate;
   @XmlElement(
      name = "Period"
   )
   protected PeriodType period;

   public BySsinType() {
   }

   public String getSsin() {
      return this.ssin;
   }

   public void setSsin(String value) {
      this.ssin = value;
   }

   public DateTime getReferenceDate() {
      return this.referenceDate;
   }

   public void setReferenceDate(DateTime value) {
      this.referenceDate = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }
}
