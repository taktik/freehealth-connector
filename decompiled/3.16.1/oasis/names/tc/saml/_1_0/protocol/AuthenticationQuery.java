package oasis.names.tc.saml._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthenticationQueryType"
)
@XmlRootElement(
   name = "AuthenticationQuery"
)
public class AuthenticationQuery extends SubjectQueryAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "AuthenticationMethod"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String authenticationMethod;

   public String getAuthenticationMethod() {
      return this.authenticationMethod;
   }

   public void setAuthenticationMethod(String value) {
      this.authenticationMethod = value;
   }
}
