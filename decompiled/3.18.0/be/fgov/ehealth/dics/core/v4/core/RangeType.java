package be.fgov.ehealth.dics.core.v4.core;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RangeType",
   propOrder = {"min", "max"}
)
public class RangeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Min",
      required = true
   )
   protected BigDecimal min;
   @XmlElement(
      name = "Max",
      required = true
   )
   protected BigDecimal max;
   @XmlAttribute(
      name = "Unit",
      required = true
   )
   protected String unit;

   public BigDecimal getMin() {
      return this.min;
   }

   public void setMin(BigDecimal value) {
      this.min = value;
   }

   public BigDecimal getMax() {
      return this.max;
   }

   public void setMax(BigDecimal value) {
      this.max = value;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String value) {
      this.unit = value;
   }
}
