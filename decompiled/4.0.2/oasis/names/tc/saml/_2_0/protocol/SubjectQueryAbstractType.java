package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Subject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectQueryAbstractType",
   propOrder = {"subject"}
)
@XmlSeeAlso({AuthzDecisionQuery.class, AuthnQuery.class, AttributeQuery.class})
public abstract class SubjectQueryAbstractType extends RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Subject",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      required = true
   )
   protected Subject subject;

   public SubjectQueryAbstractType() {
   }

   public Subject getSubject() {
      return this.subject;
   }

   public void setSubject(Subject value) {
      this.subject = value;
   }
}
