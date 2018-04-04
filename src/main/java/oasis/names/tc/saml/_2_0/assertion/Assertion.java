package oasis.names.tc.saml._2_0.assertion;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AssertionType",
   propOrder = {"issuer", "signature", "subject", "conditions", "advice", "statementsAndAuthnStatementsAndAuthzDecisionStatements"}
)
@XmlRootElement(
   name = "Assertion"
)
public class Assertion implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Issuer",
      required = true
   )
   protected NameIDType issuer;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Signature signature;
   @XmlElement(
      name = "Subject"
   )
   protected Subject subject;
   @XmlElement(
      name = "Conditions"
   )
   protected Conditions conditions;
   @XmlElement(
      name = "Advice"
   )
   protected Advice advice;
   @XmlElements({@XmlElement(
   name = "Statement"
), @XmlElement(
   name = "AuthnStatement",
   type = AuthnStatement.class
), @XmlElement(
   name = "AuthzDecisionStatement",
   type = AuthzDecisionStatement.class
), @XmlElement(
   name = "AttributeStatement",
   type = AttributeStatement.class
)})
   protected List<StatementAbstractType> statementsAndAuthnStatementsAndAuthzDecisionStatements;
   @XmlAttribute(
      name = "Version",
      required = true
   )
   protected String version;
   @XmlAttribute(
      name = "ID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "IssueInstant",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime issueInstant;

   public NameIDType getIssuer() {
      return this.issuer;
   }

   public void setIssuer(NameIDType value) {
      this.issuer = value;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }

   public Subject getSubject() {
      return this.subject;
   }

   public void setSubject(Subject value) {
      this.subject = value;
   }

   public Conditions getConditions() {
      return this.conditions;
   }

   public void setConditions(Conditions value) {
      this.conditions = value;
   }

   public Advice getAdvice() {
      return this.advice;
   }

   public void setAdvice(Advice value) {
      this.advice = value;
   }

   public List<StatementAbstractType> getStatementsAndAuthnStatementsAndAuthzDecisionStatements() {
      if (this.statementsAndAuthnStatementsAndAuthzDecisionStatements == null) {
         this.statementsAndAuthnStatementsAndAuthzDecisionStatements = new ArrayList();
      }

      return this.statementsAndAuthnStatementsAndAuthzDecisionStatements;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }

   public String getID() {
      return this.id;
   }

   public void setID(String value) {
      this.id = value;
   }

   public DateTime getIssueInstant() {
      return this.issueInstant;
   }

   public void setIssueInstant(DateTime value) {
      this.issueInstant = value;
   }
}
