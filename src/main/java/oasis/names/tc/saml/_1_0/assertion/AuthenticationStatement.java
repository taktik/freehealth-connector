package oasis.names.tc.saml._1_0.assertion;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthenticationStatementType",
   propOrder = {"subjectLocality", "authorityBindings"}
)
@XmlRootElement(
   name = "AuthenticationStatement"
)
public class AuthenticationStatement extends SubjectStatementAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SubjectLocality"
   )
   protected SubjectLocality subjectLocality;
   @XmlElement(
      name = "AuthorityBinding"
   )
   protected List<AuthorityBinding> authorityBindings;
   @XmlAttribute(
      name = "AuthenticationMethod",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String authenticationMethod;
   @XmlAttribute(
      name = "AuthenticationInstant",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime authenticationInstant;

   public SubjectLocality getSubjectLocality() {
      return this.subjectLocality;
   }

   public void setSubjectLocality(SubjectLocality value) {
      this.subjectLocality = value;
   }

   public List<AuthorityBinding> getAuthorityBindings() {
      if (this.authorityBindings == null) {
         this.authorityBindings = new ArrayList();
      }

      return this.authorityBindings;
   }

   public String getAuthenticationMethod() {
      return this.authenticationMethod;
   }

   public void setAuthenticationMethod(String value) {
      this.authenticationMethod = value;
   }

   public DateTime getAuthenticationInstant() {
      return this.authenticationInstant;
   }

   public void setAuthenticationInstant(DateTime value) {
      this.authenticationInstant = value;
   }
}
