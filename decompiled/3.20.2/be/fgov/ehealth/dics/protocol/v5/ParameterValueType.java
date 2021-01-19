package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ParameterValueType",
   propOrder = {"_boolean", "code", "strengthRange", "strength", "quantityRange", "quantity"}
)
public class ParameterValueType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Boolean"
   )
   protected Boolean _boolean;
   @XmlElement(
      name = "Code"
   )
   protected String code;
   @XmlElement(
      name = "StrengthRange"
   )
   protected StrengthRangeType strengthRange;
   @XmlElement(
      name = "Strength"
   )
   protected StrengthType strength;
   @XmlElement(
      name = "QuantityRange"
   )
   protected RangeType quantityRange;
   @XmlElement(
      name = "Quantity"
   )
   protected QuantityType quantity;

   public Boolean isBoolean() {
      return this._boolean;
   }

   public void setBoolean(Boolean value) {
      this._boolean = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public StrengthRangeType getStrengthRange() {
      return this.strengthRange;
   }

   public void setStrengthRange(StrengthRangeType value) {
      this.strengthRange = value;
   }

   public StrengthType getStrength() {
      return this.strength;
   }

   public void setStrength(StrengthType value) {
      this.strength = value;
   }

   public RangeType getQuantityRange() {
      return this.quantityRange;
   }

   public void setQuantityRange(RangeType value) {
      this.quantityRange = value;
   }

   public QuantityType getQuantity() {
      return this.quantity;
   }

   public void setQuantity(QuantityType value) {
      this.quantity = value;
   }
}
