package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultPricingUnitType",
   propOrder = {"quantity", "label"}
)
public class ConsultPricingUnitType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quantity",
      required = true
   )
   protected BigDecimal quantity;
   @XmlElement(
      name = "Label",
      required = true
   )
   protected ConsultTextType label;

   public ConsultPricingUnitType() {
   }

   public BigDecimal getQuantity() {
      return this.quantity;
   }

   public void setQuantity(BigDecimal value) {
      this.quantity = value;
   }

   public ConsultTextType getLabel() {
      return this.label;
   }

   public void setLabel(ConsultTextType value) {
      this.label = value;
   }
}
