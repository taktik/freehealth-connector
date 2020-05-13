package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractAdministrationUnitValuesCodeType;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PosologyType",
   propOrder = {"low", "high", "unit", "takes", "text"}
)
public class PosologyType extends AbstractPosologyType {
   protected BigDecimal low;
   protected BigDecimal high;
   protected AbstractAdministrationUnitValuesCodeType unit;
   protected PosologyTakesType takes;
   protected String text;

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

   public AbstractAdministrationUnitValuesCodeType getUnit() {
      return this.unit;
   }

   public void setUnit(AbstractAdministrationUnitValuesCodeType value) {
      this.unit = value;
   }

   public PosologyTakesType getTakes() {
      return this.takes;
   }

   public void setTakes(PosologyTakesType value) {
      this.takes = value;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String value) {
      this.text = value;
   }
}
