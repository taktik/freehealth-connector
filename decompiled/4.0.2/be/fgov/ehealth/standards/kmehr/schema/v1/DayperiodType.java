package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDDAYPERIOD;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dayperiodType",
   propOrder = {"cd"}
)
public class DayperiodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDDAYPERIOD cd;

   public DayperiodType() {
   }

   public CDDAYPERIOD getCd() {
      return this.cd;
   }

   public void setCd(CDDAYPERIOD value) {
      this.cd = value;
   }
}
