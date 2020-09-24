package be.apb.standards.smoa.schema.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DurationType",
   propOrder = {"decimal", "unit"}
)
public class DurationType {
   @XmlElement(
      required = true
   )
   protected BigDecimal decimal;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected CDTIMEUNIT unit;

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public CDTIMEUNIT getUnit() {
      return this.unit;
   }

   public void setUnit(CDTIMEUNIT value) {
      this.unit = value;
   }
}
