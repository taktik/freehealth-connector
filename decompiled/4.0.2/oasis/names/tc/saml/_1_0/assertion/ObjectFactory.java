package oasis.names.tc.saml._1_0.assertion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _AssertionIDReference_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "AssertionIDReference");
   private static final QName _Audience_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "Audience");
   private static final QName _Condition_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "Condition");
   private static final QName _Statement_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "Statement");
   private static final QName _SubjectStatement_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "SubjectStatement");
   private static final QName _NameIdentifier_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "NameIdentifier");
   private static final QName _ConfirmationMethod_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "ConfirmationMethod");
   private static final QName _SubjectConfirmationData_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "SubjectConfirmationData");
   private static final QName _AttributeValue_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "AttributeValue");
   private static final QName _AttributeDesignator_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:assertion", "AttributeDesignator");

   public ObjectFactory() {
   }

   public NameIdentifierType createNameIdentifierType() {
      return new NameIdentifierType();
   }

   public Assertion createAssertion() {
      return new Assertion();
   }

   public Conditions createConditions() {
      return new Conditions();
   }

   public AudienceRestrictionCondition createAudienceRestrictionCondition() {
      return new AudienceRestrictionCondition();
   }

   public DoNotCacheCondition createDoNotCacheCondition() {
      return new DoNotCacheCondition();
   }

   public Advice createAdvice() {
      return new Advice();
   }

   public AuthenticationStatement createAuthenticationStatement() {
      return new AuthenticationStatement();
   }

   public Subject createSubject() {
      return new Subject();
   }

   public SubjectConfirmation createSubjectConfirmation() {
      return new SubjectConfirmation();
   }

   public SubjectLocality createSubjectLocality() {
      return new SubjectLocality();
   }

   public AuthorityBinding createAuthorityBinding() {
      return new AuthorityBinding();
   }

   public AuthorizationDecisionStatement createAuthorizationDecisionStatement() {
      return new AuthorizationDecisionStatement();
   }

   public Action createAction() {
      return new Action();
   }

   public Evidence createEvidence() {
      return new Evidence();
   }

   public AttributeStatement createAttributeStatement() {
      return new AttributeStatement();
   }

   public Attribute createAttribute() {
      return new Attribute();
   }

   public AttributeDesignatorType createAttributeDesignatorType() {
      return new AttributeDesignatorType();
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "AssertionIDReference"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   public JAXBElement<String> createAssertionIDReference(String value) {
      return new JAXBElement(_AssertionIDReference_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "Audience"
   )
   public JAXBElement<String> createAudience(String value) {
      return new JAXBElement(_Audience_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "Condition"
   )
   public JAXBElement<ConditionAbstractType> createCondition(ConditionAbstractType value) {
      return new JAXBElement(_Condition_QNAME, ConditionAbstractType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "Statement"
   )
   public JAXBElement<StatementAbstractType> createStatement(StatementAbstractType value) {
      return new JAXBElement(_Statement_QNAME, StatementAbstractType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "SubjectStatement"
   )
   public JAXBElement<SubjectStatementAbstractType> createSubjectStatement(SubjectStatementAbstractType value) {
      return new JAXBElement(_SubjectStatement_QNAME, SubjectStatementAbstractType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "NameIdentifier"
   )
   public JAXBElement<NameIdentifierType> createNameIdentifier(NameIdentifierType value) {
      return new JAXBElement(_NameIdentifier_QNAME, NameIdentifierType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "ConfirmationMethod"
   )
   public JAXBElement<String> createConfirmationMethod(String value) {
      return new JAXBElement(_ConfirmationMethod_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "SubjectConfirmationData"
   )
   public JAXBElement<Object> createSubjectConfirmationData(Object value) {
      return new JAXBElement(_SubjectConfirmationData_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "AttributeValue"
   )
   public JAXBElement<Object> createAttributeValue(Object value) {
      return new JAXBElement(_AttributeValue_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
      name = "AttributeDesignator"
   )
   public JAXBElement<AttributeDesignatorType> createAttributeDesignator(AttributeDesignatorType value) {
      return new JAXBElement(_AttributeDesignator_QNAME, AttributeDesignatorType.class, (Class)null, value);
   }
}
