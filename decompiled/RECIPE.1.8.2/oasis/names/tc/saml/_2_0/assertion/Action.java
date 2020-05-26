package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ActionType",
   propOrder = {"value"}
)
@XmlRootElement(
   name = "Action"
)
public class Action {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Namespace",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String namespace;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public void setNamespace(String value) {
      this.namespace = value;
   }
}
