package be.fgov.ehealth.mediprima.core.v1;

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
   name = "PeriodType",
   propOrder = {"startDate", "endDate"}
)
public class PeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "StartDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlElement(
      name = "EndDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
