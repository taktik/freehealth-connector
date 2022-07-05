package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "quantityType",
   propOrder = {"decimal", "unit"}
)
public class QuantityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected BigDecimal decimal;
   protected UnitType unit;

   public QuantityType() {
   }

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public UnitType getUnit() {
      return this.unit;
   }

   public void setUnit(UnitType value) {
      this.unit = value;
   }
}
