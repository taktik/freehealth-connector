package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultParameterizedQuantityType",
   propOrder = {"quantity", "multiplier", "parameter"}
)
public class ConsultParameterizedQuantityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quantity",
      required = true
   )
   protected QuantityType quantity;
   @XmlElement(
      name = "Multiplier"
   )
   protected BigDecimal multiplier;
   @XmlElement(
      name = "Parameter"
   )
   protected ConsultDosageParameterType parameter;

   public ConsultParameterizedQuantityType() {
   }

   public QuantityType getQuantity() {
      return this.quantity;
   }

   public void setQuantity(QuantityType value) {
      this.quantity = value;
   }

   public BigDecimal getMultiplier() {
      return this.multiplier;
   }

   public void setMultiplier(BigDecimal value) {
      this.multiplier = value;
   }

   public ConsultDosageParameterType getParameter() {
      return this.parameter;
   }

   public void setParameter(ConsultDosageParameterType value) {
      this.parameter = value;
   }
}
