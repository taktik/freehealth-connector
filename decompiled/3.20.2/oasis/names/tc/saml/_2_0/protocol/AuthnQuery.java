package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthnQueryType",
   propOrder = {"requestedAuthnContext"}
)
@XmlRootElement(
   name = "AuthnQuery"
)
public class AuthnQuery extends SubjectQueryAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RequestedAuthnContext"
   )
   protected RequestedAuthnContext requestedAuthnContext;
   @XmlAttribute(
      name = "SessionIndex"
   )
   protected String sessionIndex;

   public RequestedAuthnContext getRequestedAuthnContext() {
      return this.requestedAuthnContext;
   }

   public void setRequestedAuthnContext(RequestedAuthnContext value) {
      this.requestedAuthnContext = value;
   }

   public String getSessionIndex() {
      return this.sessionIndex;
   }

   public void setSessionIndex(String value) {
      this.sessionIndex = value;
   }
}
