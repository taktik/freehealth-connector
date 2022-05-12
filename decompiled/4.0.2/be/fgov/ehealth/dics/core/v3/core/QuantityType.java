package be.fgov.ehealth.dics.core.v3.core;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "QuantityType",
   propOrder = {"value"}
)
public class QuantityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected BigDecimal value;
   @XmlAttribute(
      name = "Unit",
      required = true
   )
   protected String unit;

   public QuantityType() {
   }

   public BigDecimal getValue() {
      return this.value;
   }

   public void setValue(BigDecimal value) {
      this.value = value;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String value) {
      this.unit = value;
   }
}
