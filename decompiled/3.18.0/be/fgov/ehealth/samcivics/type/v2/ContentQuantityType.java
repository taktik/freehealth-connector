package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContentQuantityType",
   propOrder = {"value"}
)
public class ContentQuantityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected float value;
   @XmlAttribute(
      name = "unit",
      required = true
   )
   protected String unit;

   public float getValue() {
      return this.value;
   }

   public void setValue(float value) {
      this.value = value;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String value) {
      this.unit = value;
   }
}
