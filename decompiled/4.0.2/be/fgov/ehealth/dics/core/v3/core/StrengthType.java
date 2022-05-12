package be.fgov.ehealth.dics.core.v3.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StrengthType",
   propOrder = {"numerator", "denominator"}
)
public class StrengthType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Numerator",
      required = true
   )
   protected QuantityType numerator;
   @XmlElement(
      name = "Denominator",
      required = true
   )
   protected QuantityType denominator;

   public StrengthType() {
   }

   public QuantityType getNumerator() {
      return this.numerator;
   }

   public void setNumerator(QuantityType value) {
      this.numerator = value;
   }

   public QuantityType getDenominator() {
      return this.denominator;
   }

   public void setDenominator(QuantityType value) {
      this.denominator = value;
   }
}
