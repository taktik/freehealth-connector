package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"value"}
)
@XmlRootElement(
   name = "Base64Signature"
)
public class Base64Signature implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected byte[] value;
   @XmlAttribute(
      name = "Type"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;

   public Base64Signature() {
   }

   public byte[] getValue() {
      return this.value;
   }

   public void setValue(byte[] value) {
      this.value = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }
}
