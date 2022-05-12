package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AmountType",
   propOrder = {"value"}
)
public class AmountType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected float value;
   @XmlAttribute(
      name = "currency",
      required = true
   )
   protected String currency;

   public AmountType() {
   }

   public float getValue() {
      return this.value;
   }

   public void setValue(float value) {
      this.value = value;
   }

   public String getCurrency() {
      return this.currency;
   }

   public void setCurrency(String value) {
      this.currency = value;
   }
}
