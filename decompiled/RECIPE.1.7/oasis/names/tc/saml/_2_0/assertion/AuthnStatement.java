package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthnStatementType",
   propOrder = {"subjectLocality", "authnContext"}
)
@XmlRootElement(
   name = "AuthnStatement"
)
public class AuthnStatement extends StatementAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SubjectLocality"
   )
   protected SubjectLocality subjectLocality;
   @XmlElement(
      name = "AuthnContext",
      required = true
   )
   protected AuthnContext authnContext;
   @XmlAttribute(
      name = "AuthnInstant",
      required = true
   )
   @XmlJavaTypeAdapter(Adapter1.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar authnInstant;
   @XmlAttribute(
      name = "SessionIndex"
   )
   protected String sessionIndex;
   @XmlAttribute(
      name = "SessionNotOnOrAfter"
   )
   @XmlJavaTypeAdapter(Adapter1.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar sessionNotOnOrAfter;

   public SubjectLocality getSubjectLocality() {
      return this.subjectLocality;
   }

   public void setSubjectLocality(SubjectLocality value) {
      this.subjectLocality = value;
   }

   public AuthnContext getAuthnContext() {
      return this.authnContext;
   }

   public void setAuthnContext(AuthnContext value) {
      this.authnContext = value;
   }

   public Calendar getAuthnInstant() {
      return this.authnInstant;
   }

   public void setAuthnInstant(Calendar value) {
      this.authnInstant = value;
   }

   public String getSessionIndex() {
      return this.sessionIndex;
   }

   public void setSessionIndex(String value) {
      this.sessionIndex = value;
   }

   public Calendar getSessionNotOnOrAfter() {
      return this.sessionNotOnOrAfter;
   }

   public void setSessionNotOnOrAfter(Calendar value) {
      this.sessionNotOnOrAfter = value;
   }
}
