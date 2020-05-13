package be.fgov.ehealth.insurability.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ValueRefString",
   propOrder = {"value"}
)
public class ValueRefString {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "ValueRef"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String valueRef;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getValueRef() {
      return this.valueRef;
   }

   public void setValueRef(String value) {
      this.valueRef = value;
   }
}
