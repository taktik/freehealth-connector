package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AdviceType",
   propOrder = {"assertionIDRevesAndAssertionURIRevesAndAssertions"}
)
@XmlRootElement(
   name = "Advice"
)
public class Advice implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRefs({@XmlElementRef(
   name = "Assertion",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = Assertion.class
), @XmlElementRef(
   name = "EncryptedAssertion",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = JAXBElement.class
), @XmlElementRef(
   name = "AssertionIDRef",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = JAXBElement.class
)})
   @XmlAnyElement
   protected List<Object> assertionIDRevesAndAssertionURIRevesAndAssertions;

   public List<Object> getAssertionIDRevesAndAssertionURIRevesAndAssertions() {
      if (this.assertionIDRevesAndAssertionURIRevesAndAssertions == null) {
         this.assertionIDRevesAndAssertionURIRevesAndAssertions = new ArrayList();
      }

      return this.assertionIDRevesAndAssertionURIRevesAndAssertions;
   }
}
