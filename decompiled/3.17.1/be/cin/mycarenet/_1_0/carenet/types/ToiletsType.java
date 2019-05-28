package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ToiletsType",
   propOrder = {"value"}
)
public class ToiletsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Frequency",
      required = true
   )
   protected ToiletsFrequencyType frequency;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public ToiletsFrequencyType getFrequency() {
      return this.frequency;
   }

   public void setFrequency(ToiletsFrequencyType value) {
      this.frequency = value;
   }
}
