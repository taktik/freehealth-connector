package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"identifier", "value"}
)
@XmlRootElement(
   name = "Property"
)
public class Property implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Identifier",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String identifier;
   @XmlElement(
      name = "Value"
   )
   protected AnyType value;

   public String getIdentifier() {
      return this.identifier;
   }

   public void setIdentifier(String value) {
      this.identifier = value;
   }

   public AnyType getValue() {
      return this.value;
   }

   public void setValue(AnyType value) {
      this.value = value;
   }
}
