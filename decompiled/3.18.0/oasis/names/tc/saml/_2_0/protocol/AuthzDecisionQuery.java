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
import oasis.names.tc.saml._2_0.assertion.Action;
import oasis.names.tc.saml._2_0.assertion.Evidence;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthzDecisionQueryType",
   propOrder = {"actions", "evidence"}
)
@XmlRootElement(
   name = "AuthzDecisionQuery"
)
public class AuthzDecisionQuery extends SubjectQueryAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Action",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      required = true
   )
   protected List<Action> actions;
   @XmlElement(
      name = "Evidence",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected Evidence evidence;
   @XmlAttribute(
      name = "Resource",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String resource;

   public List<Action> getActions() {
      if (this.actions == null) {
         this.actions = new ArrayList();
      }

      return this.actions;
   }

   public Evidence getEvidence() {
      return this.evidence;
   }

   public void setEvidence(Evidence value) {
      this.evidence = value;
   }

   public String getResource() {
      return this.resource;
   }

   public void setResource(String value) {
      this.resource = value;
   }
}
