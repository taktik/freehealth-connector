package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CustomMetaType",
   propOrder = {"key", "value"}
)
public class CustomMetaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Key",
      required = true
   )
   protected String key;
   @XmlElement(
      name = "Value",
      required = true
   )
   protected String value;

   public CustomMetaType() {
   }

   public String getKey() {
      return this.key;
   }

   public void setKey(String value) {
      this.key = value;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
