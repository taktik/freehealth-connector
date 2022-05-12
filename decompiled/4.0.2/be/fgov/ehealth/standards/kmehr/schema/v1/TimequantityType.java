package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "timequantityType",
   propOrder = {"decimal", "unit"}
)
public class TimequantityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected BigDecimal decimal;
   protected TimeunitType unit;

   public TimequantityType() {
   }

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public TimeunitType getUnit() {
      return this.unit;
   }

   public void setUnit(TimeunitType value) {
      this.unit = value;
   }
}
