package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _AttributeStatement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AttributeStatement");

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "AttributeStatement"
   )
   public JAXBElement<AttributeStatement> createAttributeStatement(AttributeStatement value) {
      return new JAXBElement(_AttributeStatement_QNAME, AttributeStatement.class, (Class)null, value);
   }
}
