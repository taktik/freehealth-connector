package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"text", "low", "high", "unit", "takes"}
)
public class Posology implements Serializable {
   private static final long serialVersionUID = 1L;
   protected TextType text;
   protected BigDecimal low;
   protected BigDecimal high;
   protected AdministrationunitType unit;
   protected Takes takes;

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }

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

   public AdministrationunitType getUnit() {
      return this.unit;
   }

   public void setUnit(AdministrationunitType value) {
      this.unit = value;
   }

   public Takes getTakes() {
      return this.takes;
   }

   public void setTakes(Takes value) {
      this.takes = value;
   }
}
