package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttributeDesignatorType"
)
@XmlSeeAlso({Attribute.class})
public class AttributeDesignatorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "AttributeName",
      required = true
   )
   protected String attributeName;
   @XmlAttribute(
      name = "AttributeNamespace",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String attributeNamespace;

   public AttributeDesignatorType() {
   }

   public String getAttributeName() {
      return this.attributeName;
   }

   public void setAttributeName(String value) {
      this.attributeName = value;
   }

   public String getAttributeNamespace() {
      return this.attributeNamespace;
   }

   public void setAttributeNamespace(String value) {
      this.attributeNamespace = value;
   }
}
