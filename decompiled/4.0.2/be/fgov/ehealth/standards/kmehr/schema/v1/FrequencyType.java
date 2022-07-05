package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "frequencyType",
   propOrder = {"periodicity", "text", "decimal", "unit", "nominator", "denominator"}
)
public class FrequencyType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected PeriodicityType periodicity;
   protected TextType text;
   protected BigDecimal decimal;
   protected UnitType unit;
   protected Nominator nominator;
   protected Denominator denominator;

   public FrequencyType() {
   }

   public PeriodicityType getPeriodicity() {
      return this.periodicity;
   }

   public void setPeriodicity(PeriodicityType value) {
      this.periodicity = value;
   }

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
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

   public Nominator getNominator() {
      return this.nominator;
   }

   public void setNominator(Nominator value) {
      this.nominator = value;
   }

   public Denominator getDenominator() {
      return this.denominator;
   }

   public void setDenominator(Denominator value) {
      this.denominator = value;
   }
}
