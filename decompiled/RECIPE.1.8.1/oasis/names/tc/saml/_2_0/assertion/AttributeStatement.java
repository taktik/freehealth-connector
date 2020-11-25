package oasis.names.tc.saml._2_0.assertion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttributeStatementType",
   propOrder = {"attributesAndEncryptedAttributes"}
)
@XmlRootElement(
   name = "AttributeStatement"
)
public class AttributeStatement extends StatementAbstractType {
   @XmlElements({@XmlElement(
   name = "Attribute",
   type = Attribute.class
), @XmlElement(
   name = "EncryptedAttribute",
   type = EncryptedElementType.class
)})
   protected List<Object> attributesAndEncryptedAttributes;

   public List<Object> getAttributesAndEncryptedAttributes() {
      if (this.attributesAndEncryptedAttributes == null) {
         this.attributesAndEncryptedAttributes = new ArrayList();
      }

      return this.attributesAndEncryptedAttributes;
   }
}
