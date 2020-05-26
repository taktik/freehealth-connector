package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _EncryptedAssertion_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedAssertion");
   private static final QName _AssertionURIRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AssertionURIRef");
   private static final QName _AuthnContextDecl_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextDecl");
   private static final QName _AuthenticatingAuthority_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthenticatingAuthority");
   private static final QName _AuthnContextDeclRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextDeclRef");
   private static final QName _Statement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Statement");
   private static final QName _Issuer_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Issuer");
   private static final QName _Audience_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Audience");
   private static final QName _NameID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "NameID");
   private static final QName _AssertionIDRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AssertionIDRef");
   private static final QName _AttributeValue_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AttributeValue");
   private static final QName _Condition_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Condition");
   private static final QName _EncryptedAttribute_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedAttribute");
   private static final QName _SubjectConfirmationData_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "SubjectConfirmationData");
   private static final QName _EncryptedID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedID");
   private static final QName _AuthnContextClassRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextClassRef");

   public Evidence createEvidence() {
      return new Evidence();
   }

   public SubjectConfirmation createSubjectConfirmation() {
      return new SubjectConfirmation();
   }

   public AuthnStatement createAuthnStatement() {
      return new AuthnStatement();
   }

   public KeyInfoConfirmationDataType createKeyInfoConfirmationDataType() {
      return new KeyInfoConfirmationDataType();
   }

   public Subject createSubject() {
      return new Subject();
   }

   public AttributeStatement createAttributeStatement() {
      return new AttributeStatement();
   }

   public Advice createAdvice() {
      return new Advice();
   }

   public Action createAction() {
      return new Action();
   }

   public AuthzDecisionStatement createAuthzDecisionStatement() {
      return new AuthzDecisionStatement();
   }

   public Conditions createConditions() {
      return new Conditions();
   }

   public Attribute createAttribute() {
      return new Attribute();
   }

   public ProxyRestriction createProxyRestriction() {
      return new ProxyRestriction();
   }

   public SubjectLocality createSubjectLocality() {
      return new SubjectLocality();
   }

   public AuthnContext createAuthnContext() {
      return new AuthnContext();
   }

   public SubjectConfirmationDataType createSubjectConfirmationDataType() {
      return new SubjectConfirmationDataType();
   }

   public NameIDType createNameIDType() {
      return new NameIDType();
   }

   public EncryptedElementType createEncryptedElementType() {
      return new EncryptedElementType();
   }

   public AudienceRestriction createAudienceRestriction() {
      return new AudienceRestriction();
   }

   public Assertion createAssertion() {
      return new Assertion();
   }

   public OneTimeUse createOneTimeUse() {
      return new OneTimeUse();
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "EncryptedAssertion"
   )
   public JAXBElement<EncryptedElementType> createEncryptedAssertion(EncryptedElementType value) {
      return new JAXBElement(_EncryptedAssertion_QNAME, EncryptedElementType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "AssertionURIRef"
   )
   public JAXBElement<String> createAssertionURIRef(String value) {
      return new JAXBElement(_AssertionURIRef_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "AuthnContextDecl"
   )
   public JAXBElement<Object> createAuthnContextDecl(Object value) {
      return new JAXBElement(_AuthnContextDecl_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "AuthenticatingAuthority"
   )
   public JAXBElement<String> createAuthenticatingAuthority(String value) {
      return new JAXBElement(_AuthenticatingAuthority_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "AuthnContextDeclRef"
   )
   public JAXBElement<String> createAuthnContextDeclRef(String value) {
      return new JAXBElement(_AuthnContextDeclRef_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "Statement"
   )
   public JAXBElement<StatementAbstractType> createStatement(StatementAbstractType value) {
      return new JAXBElement(_Statement_QNAME, StatementAbstractType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "Issuer"
   )
   public JAXBElement<NameIDType> createIssuer(NameIDType value) {
      return new JAXBElement(_Issuer_QNAME, NameIDType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "Audience"
   )
   public JAXBElement<String> createAudience(String value) {
      return new JAXBElement(_Audience_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "NameID"
   )
   public JAXBElement<NameIDType> createNameID(NameIDType value) {
      return new JAXBElement(_NameID_QNAME, NameIDType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "AssertionIDRef"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   public JAXBElement<String> createAssertionIDRef(String value) {
      return new JAXBElement(_AssertionIDRef_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "AttributeValue"
   )
   public JAXBElement<Object> createAttributeValue(Object value) {
      return new JAXBElement(_AttributeValue_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "Condition"
   )
   public JAXBElement<ConditionAbstractType> createCondition(ConditionAbstractType value) {
      return new JAXBElement(_Condition_QNAME, ConditionAbstractType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "EncryptedAttribute"
   )
   public JAXBElement<EncryptedElementType> createEncryptedAttribute(EncryptedElementType value) {
      return new JAXBElement(_EncryptedAttribute_QNAME, EncryptedElementType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "SubjectConfirmationData"
   )
   public JAXBElement<SubjectConfirmationDataType> createSubjectConfirmationData(SubjectConfirmationDataType value) {
      return new JAXBElement(_SubjectConfirmationData_QNAME, SubjectConfirmationDataType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "EncryptedID"
   )
   public JAXBElement<EncryptedElementType> createEncryptedID(EncryptedElementType value) {
      return new JAXBElement(_EncryptedID_QNAME, EncryptedElementType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      name = "AuthnContextClassRef"
   )
   public JAXBElement<String> createAuthnContextClassRef(String value) {
      return new JAXBElement(_AuthnContextClassRef_QNAME, String.class, (Class)null, value);
   }
}
