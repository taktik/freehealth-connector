package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NihiiType",
   propOrder = {"quality", "value"}
)
public class NihiiType extends SelfRefType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quality"
   )
   protected String quality;
   @XmlElement(
      name = "Value"
   )
   protected ValueRefString value;

   public NihiiType() {
   }

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
   }

   public ValueRefString getValue() {
      return this.value;
   }

   public void setValue(ValueRefString value) {
      this.value = value;
   }
}
