package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractAdministrationUnitValuesCodeType;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegimenAdministrationQuantityType",
   propOrder = {"decimal", "unit"}
)
public class RegimenAdministrationQuantityType {
   @XmlElement(
      required = true
   )
   protected BigDecimal decimal;
   protected AbstractAdministrationUnitValuesCodeType unit;

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public AbstractAdministrationUnitValuesCodeType getUnit() {
      return this.unit;
   }

   public void setUnit(AbstractAdministrationUnitValuesCodeType value) {
      this.unit = value;
   }
}
