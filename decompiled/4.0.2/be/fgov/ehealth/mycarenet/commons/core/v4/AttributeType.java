package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttributeType",
   propOrder = {"value"}
)
public class AttributeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Value",
      required = true
   )
   protected Object value;
   @XmlAttribute(
      name = "Key",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String key;

   public AttributeType() {
   }

   public Object getValue() {
      return this.value;
   }

   public void setValue(Object value) {
      this.value = value;
   }

   public String getKey() {
      return this.key;
   }

   public void setKey(String value) {
      this.key = value;
   }
}
