package oasis.names.tc.saml._1_0.assertion;

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
   name = "AuthorizationDecisionStatementType",
   propOrder = {"actions", "evidence"}
)
@XmlRootElement(
   name = "AuthorizationDecisionStatement"
)
public class AuthorizationDecisionStatement extends SubjectStatementAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Action",
      required = true
   )
   protected List<Action> actions;
   @XmlElement(
      name = "Evidence"
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
   @XmlAttribute(
      name = "Decision",
      required = true
   )
   protected DecisionType decision;

   public AuthorizationDecisionStatement() {
   }

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

   public DecisionType getDecision() {
      return this.decision;
   }

   public void setDecision(DecisionType value) {
      this.decision = value;
   }
}
