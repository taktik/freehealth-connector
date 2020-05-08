package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SpecificTechnicalCareTreatmentType",
   propOrder = {"reason", "frequency", "product"}
)
public class SpecificTechnicalCareTreatmentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Reason"
   )
   protected String reason;
   @XmlElement(
      name = "Frequency"
   )
   protected String frequency;
   @XmlElement(
      name = "Product"
   )
   protected String product;
   @XmlAttribute(
      name = "type",
      required = true
   )
   protected SpecificTechnicalCareTypeType type;

   public String getReason() {
      return this.reason;
   }

   public void setReason(String value) {
      this.reason = value;
   }

   public String getFrequency() {
      return this.frequency;
   }

   public void setFrequency(String value) {
      this.frequency = value;
   }

   public String getProduct() {
      return this.product;
   }

   public void setProduct(String value) {
      this.product = value;
   }

   public SpecificTechnicalCareTypeType getType() {
      return this.type;
   }

   public void setType(SpecificTechnicalCareTypeType value) {
      this.type = value;
   }
}
