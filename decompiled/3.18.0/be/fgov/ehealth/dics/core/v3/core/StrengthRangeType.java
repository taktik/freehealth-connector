package be.fgov.ehealth.dics.core.v3.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StrengthRangeType",
   propOrder = {"numeratorRange", "denominator"}
)
public class StrengthRangeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NumeratorRange",
      required = true
   )
   protected RangeType numeratorRange;
   @XmlElement(
      name = "Denominator",
      required = true
   )
   protected QuantityType denominator;

   public RangeType getNumeratorRange() {
      return this.numeratorRange;
   }

   public void setNumeratorRange(RangeType value) {
      this.numeratorRange = value;
   }

   public QuantityType getDenominator() {
      return this.denominator;
   }

   public void setDenominator(QuantityType value) {
      this.denominator = value;
   }
}
