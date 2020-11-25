package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultBoundedParameterType",
   propOrder = {"dosageParameter", "lowerBound", "upperBound"}
)
public class ConsultBoundedParameterType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DosageParameter",
      required = true
   )
   protected ConsultDosageParameterType dosageParameter;
   @XmlElement(
      name = "LowerBound"
   )
   protected QuantityType lowerBound;
   @XmlElement(
      name = "UpperBound"
   )
   protected QuantityType upperBound;

   public ConsultDosageParameterType getDosageParameter() {
      return this.dosageParameter;
   }

   public void setDosageParameter(ConsultDosageParameterType value) {
      this.dosageParameter = value;
   }

   public QuantityType getLowerBound() {
      return this.lowerBound;
   }

   public void setLowerBound(QuantityType value) {
      this.lowerBound = value;
   }

   public QuantityType getUpperBound() {
      return this.upperBound;
   }

   public void setUpperBound(QuantityType value) {
      this.upperBound = value;
   }
}
