package oasis.names.tc.saml._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._1_0.assertion.AttributeDesignatorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttributeQueryType",
   propOrder = {"attributeDesignators"}
)
@XmlRootElement(
   name = "AttributeQuery"
)
public class AttributeQuery extends SubjectQueryAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AttributeDesignator",
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion"
   )
   protected List<AttributeDesignatorType> attributeDesignators;
   @XmlAttribute(
      name = "Resource"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String resource;

   public AttributeQuery() {
   }

   public List<AttributeDesignatorType> getAttributeDesignators() {
      if (this.attributeDesignators == null) {
         this.attributeDesignators = new ArrayList();
      }

      return this.attributeDesignators;
   }

   public String getResource() {
      return this.resource;
   }

   public void setResource(String value) {
      this.resource = value;
   }
}
