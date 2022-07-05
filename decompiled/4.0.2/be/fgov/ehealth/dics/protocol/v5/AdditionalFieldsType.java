package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AdditionalFieldsType",
   propOrder = {"key", "value"}
)
public class AdditionalFieldsType implements Serializable {
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

   public AdditionalFieldsType() {
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
