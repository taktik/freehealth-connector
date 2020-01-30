package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "property",
   propOrder = {"key", "value", "valuebase64"}
)
public class Property {
   @XmlElement(
      required = true
   )
   protected String key;
   protected String value;
   protected byte[] valuebase64;

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

   public byte[] getValuebase64() {
      return this.valuebase64;
   }

   public void setValuebase64(byte[] value) {
      this.valuebase64 = value;
   }
}
