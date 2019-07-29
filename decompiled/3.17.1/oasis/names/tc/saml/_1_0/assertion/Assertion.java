package oasis.names.tc.saml._1_0.assertion;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
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
   propOrder = {"conditions", "advice", "statementsAndSubjectStatementsAndAuthenticationStatements", "signature"}
)
@XmlRootElement(
   name = "Assertion"
)
public class Assertion implements Serializable {
   private static final long serialVersionUID = 1L;
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
   name = "SubjectStatement",
   type = SubjectStatementAbstractType.class
), @XmlElement(
   name = "AuthenticationStatement",
   type = AuthenticationStatement.class
), @XmlElement(
   name = "AuthorizationDecisionStatement",
   type = AuthorizationDecisionStatement.class
), @XmlElement(
   name = "AttributeStatement",
   type = AttributeStatement.class
)})
   protected List<StatementAbstractType> statementsAndSubjectStatementsAndAuthenticationStatements;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Signature signature;
   @XmlAttribute(
      name = "MajorVersion",
      required = true
   )
   protected BigInteger majorVersion;
   @XmlAttribute(
      name = "MinorVersion",
      required = true
   )
   protected BigInteger minorVersion;
   @XmlAttribute(
      name = "AssertionID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String assertionID;
   @XmlAttribute(
      name = "Issuer",
      required = true
   )
   protected String issuer;
   @XmlAttribute(
      name = "IssueInstant",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime issueInstant;

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

   public List<StatementAbstractType> getStatementsAndSubjectStatementsAndAuthenticationStatements() {
      if (this.statementsAndSubjectStatementsAndAuthenticationStatements == null) {
         this.statementsAndSubjectStatementsAndAuthenticationStatements = new ArrayList();
      }

      return this.statementsAndSubjectStatementsAndAuthenticationStatements;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }

   public BigInteger getMajorVersion() {
      return this.majorVersion;
   }

   public void setMajorVersion(BigInteger value) {
      this.majorVersion = value;
   }

   public BigInteger getMinorVersion() {
      return this.minorVersion;
   }

   public void setMinorVersion(BigInteger value) {
      this.minorVersion = value;
   }

   public String getAssertionID() {
      return this.assertionID;
   }

   public void setAssertionID(String value) {
      this.assertionID = value;
   }

   public String getIssuer() {
      return this.issuer;
   }

   public void setIssuer(String value) {
      this.issuer = value;
   }

   public DateTime getIssueInstant() {
      return this.issueInstant;
   }

   public void setIssueInstant(DateTime value) {
      this.issueInstant = value;
   }
}
