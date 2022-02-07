
package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the oasis.names.tc.saml._2_0.assertion package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AuthnContextDecl_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextDecl");
    private final static QName _AuthnContextDeclRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextDeclRef");
    private final static QName _Evidence_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Evidence");
    private final static QName _Issuer_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Issuer");
    private final static QName _Attribute_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Attribute");
    private final static QName _Action_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Action");
    private final static QName _AuthnContext_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContext");
    private final static QName _EncryptedID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedID");
    private final static QName _EncryptedAssertion_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedAssertion");
    private final static QName _Statement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Statement");
    private final static QName _AuthzDecisionStatement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthzDecisionStatement");
    private final static QName _BaseID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "BaseID");
    private final static QName _Condition_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Condition");
    private final static QName _EncryptedAttribute_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedAttribute");
    private final static QName _Subject_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Subject");
    private final static QName _SubjectConfirmation_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "SubjectConfirmation");
    private final static QName _AudienceRestriction_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AudienceRestriction");
    private final static QName _OneTimeUse_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "OneTimeUse");
    private final static QName _AttributeValue_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AttributeValue");
    private final static QName _Conditions_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Conditions");
    private final static QName _SubjectLocality_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "SubjectLocality");
    private final static QName _SubjectConfirmationData_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "SubjectConfirmationData");
    private final static QName _AttributeStatement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AttributeStatement");
    private final static QName _AssertionURIRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AssertionURIRef");
    private final static QName _AuthenticatingAuthority_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthenticatingAuthority");
    private final static QName _Advice_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Advice");
    private final static QName _AuthnStatement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnStatement");
    private final static QName _NameID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "NameID");
    private final static QName _AssertionIDRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AssertionIDRef");
    private final static QName _Audience_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Audience");
    private final static QName _ProxyRestriction_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "ProxyRestriction");
    private final static QName _Assertion_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion");
    private final static QName _AuthnContextClassRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextClassRef");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.saml._2_0.assertion
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EncryptedElementType }
     *
     */
    public EncryptedElementType createEncryptedElementType() {
        return new EncryptedElementType();
    }

    /**
     * Create an instance of {@link Action }
     *
     */
    public Action createAction() {
        return new Action();
    }

    /**
     * Create an instance of {@link NameIDType }
     *
     */
    public NameIDType createNameIDType() {
        return new NameIDType();
    }

    /**
     * Create an instance of {@link Attribute }
     *
     */
    public Attribute createAttribute() {
        return new Attribute();
    }

    /**
     * Create an instance of {@link SubjectConfirmationDataType }
     *
     */
    public SubjectConfirmationDataType createSubjectConfirmationDataType() {
        return new SubjectConfirmationDataType();
    }

    /**
     * Create an instance of {@link Conditions }
     *
     */
    public Conditions createConditions() {
        return new Conditions();
    }

    /**
     * Create an instance of {@link SubjectLocality }
     *
     */
    public SubjectLocality createSubjectLocality() {
        return new SubjectLocality();
    }

    /**
     * Create an instance of {@link AuthnContext }
     *
     */
    public AuthnContext createAuthnContext() {
        return new AuthnContext();
    }

    /**
     * Create an instance of {@link AudienceRestriction }
     *
     */
    public AudienceRestriction createAudienceRestriction() {
        return new AudienceRestriction();
    }

    /**
     * Create an instance of {@link SubjectConfirmation }
     *
     */
    public SubjectConfirmation createSubjectConfirmation() {
        return new SubjectConfirmation();
    }

    /**
     * Create an instance of {@link Evidence }
     *
     */
    public Evidence createEvidence() {
        return new Evidence();
    }

    /**
     * Create an instance of {@link OneTimeUse }
     *
     */
    public OneTimeUse createOneTimeUse() {
        return new OneTimeUse();
    }

    /**
     * Create an instance of {@link AuthzDecisionStatement }
     *
     */
    public AuthzDecisionStatement createAuthzDecisionStatement() {
        return new AuthzDecisionStatement();
    }

    /**
     * Create an instance of {@link Assertion }
     *
     */
    public Assertion createAssertion() {
        return new Assertion();
    }

    /**
     * Create an instance of {@link ProxyRestriction }
     *
     */
    public ProxyRestriction createProxyRestriction() {
        return new ProxyRestriction();
    }

    /**
     * Create an instance of {@link Subject }
     *
     */
    public Subject createSubject() {
        return new Subject();
    }

    /**
     * Create an instance of {@link AttributeStatement }
     *
     */
    public AttributeStatement createAttributeStatement() {
        return new AttributeStatement();
    }

    /**
     * Create an instance of {@link AuthnStatement }
     *
     */
    public AuthnStatement createAuthnStatement() {
        return new AuthnStatement();
    }

    /**
     * Create an instance of {@link Advice }
     *
     */
    public Advice createAdvice() {
        return new Advice();
    }

    /**
     * Create an instance of {@link KeyInfoConfirmationDataType }
     *
     */
    public KeyInfoConfirmationDataType createKeyInfoConfirmationDataType() {
        return new KeyInfoConfirmationDataType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnContextDecl")
    public JAXBElement<Object> createAuthnContextDecl(Object value) {
        return new JAXBElement<Object>(_AuthnContextDecl_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnContextDeclRef")
    public JAXBElement<String> createAuthnContextDeclRef(String value) {
        return new JAXBElement<String>(_AuthnContextDeclRef_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Evidence }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Evidence")
    public JAXBElement<Evidence> createEvidence(Evidence value) {
        return new JAXBElement<Evidence>(_Evidence_QNAME, Evidence.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameIDType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Issuer")
    public JAXBElement<NameIDType> createIssuer(NameIDType value) {
        return new JAXBElement<NameIDType>(_Issuer_QNAME, NameIDType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Attribute }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Attribute")
    public JAXBElement<Attribute> createAttribute(Attribute value) {
        return new JAXBElement<Attribute>(_Attribute_QNAME, Attribute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Action }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Action")
    public JAXBElement<Action> createAction(Action value) {
        return new JAXBElement<Action>(_Action_QNAME, Action.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthnContext }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnContext")
    public JAXBElement<AuthnContext> createAuthnContext(AuthnContext value) {
        return new JAXBElement<AuthnContext>(_AuthnContext_QNAME, AuthnContext.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "EncryptedID")
    public JAXBElement<EncryptedElementType> createEncryptedID(EncryptedElementType value) {
        return new JAXBElement<EncryptedElementType>(_EncryptedID_QNAME, EncryptedElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "EncryptedAssertion")
    public JAXBElement<EncryptedElementType> createEncryptedAssertion(EncryptedElementType value) {
        return new JAXBElement<EncryptedElementType>(_EncryptedAssertion_QNAME, EncryptedElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatementAbstractType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Statement")
    public JAXBElement<StatementAbstractType> createStatement(StatementAbstractType value) {
        return new JAXBElement<StatementAbstractType>(_Statement_QNAME, StatementAbstractType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthzDecisionStatement }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthzDecisionStatement")
    public JAXBElement<AuthzDecisionStatement> createAuthzDecisionStatement(AuthzDecisionStatement value) {
        return new JAXBElement<AuthzDecisionStatement>(_AuthzDecisionStatement_QNAME, AuthzDecisionStatement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseID }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "BaseID")
    public JAXBElement<BaseID> createBaseID(BaseID value) {
        return new JAXBElement<BaseID>(_BaseID_QNAME, BaseID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConditionAbstractType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Condition")
    public JAXBElement<ConditionAbstractType> createCondition(ConditionAbstractType value) {
        return new JAXBElement<ConditionAbstractType>(_Condition_QNAME, ConditionAbstractType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "EncryptedAttribute")
    public JAXBElement<EncryptedElementType> createEncryptedAttribute(EncryptedElementType value) {
        return new JAXBElement<EncryptedElementType>(_EncryptedAttribute_QNAME, EncryptedElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Subject }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Subject")
    public JAXBElement<Subject> createSubject(Subject value) {
        return new JAXBElement<Subject>(_Subject_QNAME, Subject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectConfirmation }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "SubjectConfirmation")
    public JAXBElement<SubjectConfirmation> createSubjectConfirmation(SubjectConfirmation value) {
        return new JAXBElement<SubjectConfirmation>(_SubjectConfirmation_QNAME, SubjectConfirmation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AudienceRestriction }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AudienceRestriction")
    public JAXBElement<AudienceRestriction> createAudienceRestriction(AudienceRestriction value) {
        return new JAXBElement<AudienceRestriction>(_AudienceRestriction_QNAME, AudienceRestriction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OneTimeUse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "OneTimeUse")
    public JAXBElement<OneTimeUse> createOneTimeUse(OneTimeUse value) {
        return new JAXBElement<OneTimeUse>(_OneTimeUse_QNAME, OneTimeUse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AttributeValue")
    public JAXBElement<Object> createAttributeValue(Object value) {
        return new JAXBElement<Object>(_AttributeValue_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Conditions }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Conditions")
    public JAXBElement<Conditions> createConditions(Conditions value) {
        return new JAXBElement<Conditions>(_Conditions_QNAME, Conditions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectLocality }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "SubjectLocality")
    public JAXBElement<SubjectLocality> createSubjectLocality(SubjectLocality value) {
        return new JAXBElement<SubjectLocality>(_SubjectLocality_QNAME, SubjectLocality.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectConfirmationDataType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "SubjectConfirmationData")
    public JAXBElement<SubjectConfirmationDataType> createSubjectConfirmationData(SubjectConfirmationDataType value) {
        return new JAXBElement<SubjectConfirmationDataType>(_SubjectConfirmationData_QNAME, SubjectConfirmationDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeStatement }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AttributeStatement")
    public JAXBElement<AttributeStatement> createAttributeStatement(AttributeStatement value) {
        return new JAXBElement<AttributeStatement>(_AttributeStatement_QNAME, AttributeStatement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AssertionURIRef")
    public JAXBElement<String> createAssertionURIRef(String value) {
        return new JAXBElement<String>(_AssertionURIRef_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthenticatingAuthority")
    public JAXBElement<String> createAuthenticatingAuthority(String value) {
        return new JAXBElement<String>(_AuthenticatingAuthority_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Advice }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Advice")
    public JAXBElement<Advice> createAdvice(Advice value) {
        return new JAXBElement<Advice>(_Advice_QNAME, Advice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthnStatement }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnStatement")
    public JAXBElement<AuthnStatement> createAuthnStatement(AuthnStatement value) {
        return new JAXBElement<AuthnStatement>(_AuthnStatement_QNAME, AuthnStatement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameIDType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "NameID")
    public JAXBElement<NameIDType> createNameID(NameIDType value) {
        return new JAXBElement<NameIDType>(_NameID_QNAME, NameIDType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AssertionIDRef")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssertionIDRef(String value) {
        return new JAXBElement<String>(_AssertionIDRef_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Audience")
    public JAXBElement<String> createAudience(String value) {
        return new JAXBElement<String>(_Audience_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProxyRestriction }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "ProxyRestriction")
    public JAXBElement<ProxyRestriction> createProxyRestriction(ProxyRestriction value) {
        return new JAXBElement<ProxyRestriction>(_ProxyRestriction_QNAME, ProxyRestriction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Assertion }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Assertion")
    public JAXBElement<Assertion> createAssertion(Assertion value) {
        return new JAXBElement<Assertion>(_Assertion_QNAME, Assertion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnContextClassRef")
    public JAXBElement<String> createAuthnContextClassRef(String value) {
        return new JAXBElement<String>(_AuthnContextClassRef_QNAME, String.class, null, value);
    }

}
