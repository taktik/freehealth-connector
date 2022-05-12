package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "QuarterPeriodType",
   propOrder = {"beginQuarter", "endQuarter"}
)
public class QuarterPeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BeginQuarter",
      required = true
   )
   protected String beginQuarter;
   @XmlElement(
      name = "EndQuarter",
      required = true
   )
   protected String endQuarter;

   public QuarterPeriodType() {
   }

   public String getBeginQuarter() {
      return this.beginQuarter;
   }

   public void setBeginQuarter(String value) {
      this.beginQuarter = value;
   }

   public String getEndQuarter() {
      return this.endQuarter;
   }

   public void setEndQuarter(String value) {
      this.endQuarter = value;
   }
}
