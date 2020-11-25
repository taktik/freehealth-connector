package oasis.names.tc.saml._2_0.assertion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EvidenceType",
   propOrder = {"assertionIDRevesAndAssertionURIRevesAndAssertions"}
)
@XmlRootElement(
   name = "Evidence"
)
public class Evidence {
   @XmlElementRefs({@XmlElementRef(
   name = "Assertion",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = Assertion.class,
   required = false
), @XmlElementRef(
   name = "AssertionIDRef",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = JAXBElement.class,
   required = false
), @XmlElementRef(
   name = "AssertionURIRef",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = JAXBElement.class,
   required = false
), @XmlElementRef(
   name = "EncryptedAssertion",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = JAXBElement.class,
   required = false
)})
   protected List<Object> assertionIDRevesAndAssertionURIRevesAndAssertions;

   public List<Object> getAssertionIDRevesAndAssertionURIRevesAndAssertions() {
      if (this.assertionIDRevesAndAssertionURIRevesAndAssertions == null) {
         this.assertionIDRevesAndAssertionURIRevesAndAssertions = new ArrayList();
      }

      return this.assertionIDRevesAndAssertionURIRevesAndAssertions;
   }
}
