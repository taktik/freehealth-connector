package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "administrationquantityType",
   propOrder = {"decimal", "unit"}
)
public class AdministrationquantityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected BigDecimal decimal;
   protected AdministrationunitType unit;

   public AdministrationquantityType() {
   }

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public AdministrationunitType getUnit() {
      return this.unit;
   }

   public void setUnit(AdministrationunitType value) {
      this.unit = value;
   }
}
