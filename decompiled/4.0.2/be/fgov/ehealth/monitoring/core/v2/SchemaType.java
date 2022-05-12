package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SchemaType"
)
public class SchemaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Value"
   )
   protected String value;

   public SchemaType() {
   }

   public String getValue() {
      return this.value == null ? "1.2" : this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
