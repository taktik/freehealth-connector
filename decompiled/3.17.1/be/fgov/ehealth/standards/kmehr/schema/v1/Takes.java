package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"low", "high"}
)
public class Takes implements Serializable {
   private static final long serialVersionUID = 1L;
   protected BigDecimal low;
   @XmlElement(
      required = true
   )
   protected BigDecimal high;

   public BigDecimal getLow() {
      return this.low;
   }

   public void setLow(BigDecimal value) {
      this.low = value;
   }

   public BigDecimal getHigh() {
      return this.high;
   }

   public void setHigh(BigDecimal value) {
      this.high = value;
   }
}
