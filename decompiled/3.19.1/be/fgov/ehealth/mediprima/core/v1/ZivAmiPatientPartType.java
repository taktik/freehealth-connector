package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ZivAmiPatientPartType",
   propOrder = {"zivAmiPart", "patientPart"}
)
public class ZivAmiPatientPartType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ZivAmiPart",
      required = true
   )
   protected BigDecimal zivAmiPart;
   @XmlElement(
      name = "PatientPart",
      required = true
   )
   protected BigDecimal patientPart;

   public BigDecimal getZivAmiPart() {
      return this.zivAmiPart;
   }

   public void setZivAmiPart(BigDecimal value) {
      this.zivAmiPart = value;
   }

   public BigDecimal getPatientPart() {
      return this.patientPart;
   }

   public void setPatientPart(BigDecimal value) {
      this.patientPart = value;
   }
}
