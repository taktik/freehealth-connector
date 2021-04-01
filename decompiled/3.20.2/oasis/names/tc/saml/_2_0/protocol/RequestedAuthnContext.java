package oasis.names.tc.saml._2_0.protocol;

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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestedAuthnContextType",
   propOrder = {"authnContextDeclReves", "authnContextClassReves"}
)
@XmlRootElement(
   name = "RequestedAuthnContext"
)
public class RequestedAuthnContext implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AuthnContextDeclRef",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> authnContextDeclReves;
   @XmlElement(
      name = "AuthnContextClassRef",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> authnContextClassReves;
   @XmlAttribute(
      name = "Comparison"
   )
   protected AuthnContextComparisonType comparison;

   public List<String> getAuthnContextDeclReves() {
      if (this.authnContextDeclReves == null) {
         this.authnContextDeclReves = new ArrayList();
      }

      return this.authnContextDeclReves;
   }

   public List<String> getAuthnContextClassReves() {
      if (this.authnContextClassReves == null) {
         this.authnContextClassReves = new ArrayList();
      }

      return this.authnContextClassReves;
   }

   public AuthnContextComparisonType getComparison() {
      return this.comparison;
   }

   public void setComparison(AuthnContextComparisonType value) {
      this.comparison = value;
   }
}
