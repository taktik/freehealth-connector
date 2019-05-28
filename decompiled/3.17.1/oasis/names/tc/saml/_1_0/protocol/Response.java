package oasis.names.tc.saml._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._1_0.assertion.Assertion;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"status", "assertions"}
)
@XmlRootElement(
   name = "Response"
)
public class Response extends ResponseAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Status",
      required = true
   )
   protected Status status;
   @XmlElement(
      name = "Assertion",
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion"
   )
   protected List<Assertion> assertions;

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status value) {
      this.status = value;
   }

   public List<Assertion> getAssertions() {
      if (this.assertions == null) {
         this.assertions = new ArrayList();
      }

      return this.assertions;
   }
}
