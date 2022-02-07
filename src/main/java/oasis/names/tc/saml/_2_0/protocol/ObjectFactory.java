
package oasis.names.tc.saml._2_0.protocol;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import oasis.names.tc.saml._2_0.assertion.EncryptedElementType;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the oasis.names.tc.saml._2_0.protocol package.
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

    private final static QName _IDPEntry_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "IDPEntry");
    private final static QName _RequestedAuthnContext_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "RequestedAuthnContext");
    private final static QName _AttributeQuery_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "AttributeQuery");
    private final static QName _StatusDetail_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "StatusDetail");
    private final static QName _ArtifactResponse_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "ArtifactResponse");
    private final static QName _NameIDMappingRequest_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "NameIDMappingRequest");
    private final static QName _Status_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "Status");
    private final static QName _LogoutRequest_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "LogoutRequest");
    private final static QName _AuthnQuery_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "AuthnQuery");
    private final static QName _SubjectQuery_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "SubjectQuery");
    private final static QName _Scoping_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "Scoping");
    private final static QName _RequesterID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "RequesterID");
    private final static QName _StatusCode_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "StatusCode");
    private final static QName _Terminate_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "Terminate");
    private final static QName _GetComplete_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "GetComplete");
    private final static QName _ManageNameIDResponse_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "ManageNameIDResponse");
    private final static QName _Response_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "Response");
    private final static QName _AuthnRequest_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "AuthnRequest");
    private final static QName _NewEncryptedID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "NewEncryptedID");
    private final static QName _NameIDMappingResponse_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "NameIDMappingResponse");
    private final static QName _IDPList_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "IDPList");
    private final static QName _StatusMessage_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "StatusMessage");
    private final static QName _Artifact_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "Artifact");
    private final static QName _NewID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "NewID");
    private final static QName _NameIDPolicy_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "NameIDPolicy");
    private final static QName _LogoutResponse_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "LogoutResponse");
    private final static QName _AuthzDecisionQuery_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "AuthzDecisionQuery");
    private final static QName _AssertionIDRequest_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "AssertionIDRequest");
    private final static QName _ArtifactResolve_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "ArtifactResolve");
    private final static QName _SessionIndex_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "SessionIndex");
    private final static QName _Extensions_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "Extensions");
    private final static QName _ManageNameIDRequest_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "ManageNameIDRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.saml._2_0.protocol
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ManageNameIDRequest }
     *
     */
    public ManageNameIDRequest createManageNameIDRequest() {
        return new ManageNameIDRequest();
    }

    /**
     * Create an instance of {@link ExtensionsType }
     *
     */
    public ExtensionsType createExtensionsType() {
        return new ExtensionsType();
    }

    /**
     * Create an instance of {@link StatusCode }
     *
     */
    public StatusCode createStatusCode() {
        return new StatusCode();
    }

    /**
     * Create an instance of {@link Terminate }
     *
     */
    public Terminate createTerminate() {
        return new Terminate();
    }

    /**
     * Create an instance of {@link AssertionIDRequest }
     *
     */
    public AssertionIDRequest createAssertionIDRequest() {
        return new AssertionIDRequest();
    }

    /**
     * Create an instance of {@link ArtifactResolve }
     *
     */
    public ArtifactResolve createArtifactResolve() {
        return new ArtifactResolve();
    }

    /**
     * Create an instance of {@link Scoping }
     *
     */
    public Scoping createScoping() {
        return new Scoping();
    }

    /**
     * Create an instance of {@link LogoutRequest }
     *
     */
    public LogoutRequest createLogoutRequest() {
        return new LogoutRequest();
    }

    /**
     * Create an instance of {@link AuthnQuery }
     *
     */
    public AuthnQuery createAuthnQuery() {
        return new AuthnQuery();
    }

    /**
     * Create an instance of {@link Status }
     *
     */
    public Status createStatus() {
        return new Status();
    }

    /**
     * Create an instance of {@link StatusResponseType }
     *
     */
    public StatusResponseType createStatusResponseType() {
        return new StatusResponseType();
    }

    /**
     * Create an instance of {@link NameIDPolicy }
     *
     */
    public NameIDPolicy createNameIDPolicy() {
        return new NameIDPolicy();
    }

    /**
     * Create an instance of {@link AuthzDecisionQuery }
     *
     */
    public AuthzDecisionQuery createAuthzDecisionQuery() {
        return new AuthzDecisionQuery();
    }

    /**
     * Create an instance of {@link IDPList }
     *
     */
    public IDPList createIDPList() {
        return new IDPList();
    }

    /**
     * Create an instance of {@link NameIDMappingRequest }
     *
     */
    public NameIDMappingRequest createNameIDMappingRequest() {
        return new NameIDMappingRequest();
    }

    /**
     * Create an instance of {@link Response }
     *
     */
    public Response createResponseType() {
        return new Response();
    }

    /**
     * Create an instance of {@link RequestedAuthnContext }
     *
     */
    public RequestedAuthnContext createRequestedAuthnContext() {
        return new RequestedAuthnContext();
    }

    /**
     * Create an instance of {@link AuthnRequest }
     *
     */
    public AuthnRequest createAuthnRequest() {
        return new AuthnRequest();
    }

    /**
     * Create an instance of {@link AttributeQuery }
     *
     */
    public AttributeQuery createAttributeQueryType() {
        return new AttributeQuery();
    }

    /**
     * Create an instance of {@link ArtifactResponse }
     *
     */
    public ArtifactResponse createArtifactResponse() {
        return new ArtifactResponse();
    }

    /**
     * Create an instance of {@link NameIDMappingResponse }
     *
     */
    public NameIDMappingResponse createNameIDMappingResponse() {
        return new NameIDMappingResponse();
    }

    /**
     * Create an instance of {@link StatusDetail }
     *
     */
    public StatusDetail createStatusDetail() {
        return new StatusDetail();
    }

    /**
     * Create an instance of {@link IDPEntry }
     *
     */
    public IDPEntry createIDPEntry() {
        return new IDPEntry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IDPEntry }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "IDPEntry")
    public JAXBElement<IDPEntry> createIDPEntry(IDPEntry value) {
        return new JAXBElement<IDPEntry>(_IDPEntry_QNAME, IDPEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestedAuthnContext }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "RequestedAuthnContext")
    public JAXBElement<RequestedAuthnContext> createRequestedAuthnContext(RequestedAuthnContext value) {
        return new JAXBElement<RequestedAuthnContext>(_RequestedAuthnContext_QNAME, RequestedAuthnContext.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeQuery }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "AttributeQuery")
    public JAXBElement<AttributeQuery> createAttributeQuery(AttributeQuery value) {
        return new JAXBElement<AttributeQuery>(_AttributeQuery_QNAME, AttributeQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusDetail }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "StatusDetail")
    public JAXBElement<StatusDetail> createStatusDetail(StatusDetail value) {
        return new JAXBElement<StatusDetail>(_StatusDetail_QNAME, StatusDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArtifactResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "ArtifactResponse")
    public JAXBElement<ArtifactResponse> createArtifactResponse(ArtifactResponse value) {
        return new JAXBElement<ArtifactResponse>(_ArtifactResponse_QNAME, ArtifactResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameIDMappingRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "NameIDMappingRequest")
    public JAXBElement<NameIDMappingRequest> createNameIDMappingRequest(NameIDMappingRequest value) {
        return new JAXBElement<NameIDMappingRequest>(_NameIDMappingRequest_QNAME, NameIDMappingRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Status }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "Status")
    public JAXBElement<Status> createStatus(Status value) {
        return new JAXBElement<Status>(_Status_QNAME, Status.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "LogoutRequest")
    public JAXBElement<LogoutRequest> createLogoutRequest(LogoutRequest value) {
        return new JAXBElement<LogoutRequest>(_LogoutRequest_QNAME, LogoutRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthnQuery }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "AuthnQuery")
    public JAXBElement<AuthnQuery> createAuthnQuery(AuthnQuery value) {
        return new JAXBElement<AuthnQuery>(_AuthnQuery_QNAME, AuthnQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectQueryAbstractType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "SubjectQuery")
    public JAXBElement<SubjectQueryAbstractType> createSubjectQuery(SubjectQueryAbstractType value) {
        return new JAXBElement<SubjectQueryAbstractType>(_SubjectQuery_QNAME, SubjectQueryAbstractType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Scoping }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "Scoping")
    public JAXBElement<Scoping> createScoping(Scoping value) {
        return new JAXBElement<Scoping>(_Scoping_QNAME, Scoping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "RequesterID")
    public JAXBElement<String> createRequesterID(String value) {
        return new JAXBElement<String>(_RequesterID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusCode }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "StatusCode")
    public JAXBElement<StatusCode> createStatusCode(StatusCode value) {
        return new JAXBElement<StatusCode>(_StatusCode_QNAME, StatusCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Terminate }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "Terminate")
    public JAXBElement<Terminate> createTerminate(Terminate value) {
        return new JAXBElement<Terminate>(_Terminate_QNAME, Terminate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "GetComplete")
    public JAXBElement<String> createGetComplete(String value) {
        return new JAXBElement<String>(_GetComplete_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusResponseType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "ManageNameIDResponse")
    public JAXBElement<StatusResponseType> createManageNameIDResponse(StatusResponseType value) {
        return new JAXBElement<StatusResponseType>(_ManageNameIDResponse_QNAME, StatusResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "Response")
    public JAXBElement<Response> createResponse(Response value) {
        return new JAXBElement<Response>(_Response_QNAME, Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthnRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "AuthnRequest")
    public JAXBElement<AuthnRequest> createAuthnRequest(AuthnRequest value) {
        return new JAXBElement<AuthnRequest>(_AuthnRequest_QNAME, AuthnRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "NewEncryptedID")
    public JAXBElement<EncryptedElementType> createNewEncryptedID(EncryptedElementType value) {
        return new JAXBElement<EncryptedElementType>(_NewEncryptedID_QNAME, EncryptedElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameIDMappingResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "NameIDMappingResponse")
    public JAXBElement<NameIDMappingResponse> createNameIDMappingResponse(NameIDMappingResponse value) {
        return new JAXBElement<NameIDMappingResponse>(_NameIDMappingResponse_QNAME, NameIDMappingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IDPList }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "IDPList")
    public JAXBElement<IDPList> createIDPList(IDPList value) {
        return new JAXBElement<IDPList>(_IDPList_QNAME, IDPList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "StatusMessage")
    public JAXBElement<String> createStatusMessage(String value) {
        return new JAXBElement<String>(_StatusMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "Artifact")
    public JAXBElement<String> createArtifact(String value) {
        return new JAXBElement<String>(_Artifact_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "NewID")
    public JAXBElement<String> createNewID(String value) {
        return new JAXBElement<String>(_NewID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameIDPolicy }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "NameIDPolicy")
    public JAXBElement<NameIDPolicy> createNameIDPolicy(NameIDPolicy value) {
        return new JAXBElement<NameIDPolicy>(_NameIDPolicy_QNAME, NameIDPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusResponseType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "LogoutResponse")
    public JAXBElement<StatusResponseType> createLogoutResponse(StatusResponseType value) {
        return new JAXBElement<StatusResponseType>(_LogoutResponse_QNAME, StatusResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthzDecisionQuery }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "AuthzDecisionQuery")
    public JAXBElement<AuthzDecisionQuery> createAuthzDecisionQuery(AuthzDecisionQuery value) {
        return new JAXBElement<AuthzDecisionQuery>(_AuthzDecisionQuery_QNAME, AuthzDecisionQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssertionIDRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "AssertionIDRequest")
    public JAXBElement<AssertionIDRequest> createAssertionIDRequest(AssertionIDRequest value) {
        return new JAXBElement<AssertionIDRequest>(_AssertionIDRequest_QNAME, AssertionIDRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArtifactResolve }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "ArtifactResolve")
    public JAXBElement<ArtifactResolve> createArtifactResolve(ArtifactResolve value) {
        return new JAXBElement<ArtifactResolve>(_ArtifactResolve_QNAME, ArtifactResolve.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "SessionIndex")
    public JAXBElement<String> createSessionIndex(String value) {
        return new JAXBElement<String>(_SessionIndex_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtensionsType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "Extensions")
    public JAXBElement<ExtensionsType> createExtensions(ExtensionsType value) {
        return new JAXBElement<ExtensionsType>(_Extensions_QNAME, ExtensionsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManageNameIDRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:protocol", name = "ManageNameIDRequest")
    public JAXBElement<ManageNameIDRequest> createManageNameIDRequest(ManageNameIDRequest value) {
        return new JAXBElement<ManageNameIDRequest>(_ManageNameIDRequest_QNAME, ManageNameIDRequest.class, null, value);
    }

}
