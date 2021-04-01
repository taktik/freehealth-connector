package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultAdministrationFrequencyType",
   propOrder = {"quantity", "timeframe"}
)
public class ConsultAdministrationFrequencyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quantity"
   )
   protected int quantity;
   @XmlElement(
      name = "Timeframe",
      required = true
   )
   protected QuantityType timeframe;

   public int getQuantity() {
      return this.quantity;
   }

   public void setQuantity(int value) {
      this.quantity = value;
   }

   public QuantityType getTimeframe() {
      return this.timeframe;
   }

   public void setTimeframe(QuantityType value) {
      this.timeframe = value;
   }
}
