package oasis.names.tc.saml._2_0.protocol;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import oasis.names.tc.saml._2_0.assertion.EncryptedElementType;

@XmlRegistry
public class ObjectFactory {
   private static final QName _Extensions_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "Extensions");
   private static final QName _StatusMessage_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "StatusMessage");
   private static final QName _SubjectQuery_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "SubjectQuery");
   private static final QName _GetComplete_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "GetComplete");
   private static final QName _RequesterID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "RequesterID");
   private static final QName _Artifact_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "Artifact");
   private static final QName _NewEncryptedID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "NewEncryptedID");
   private static final QName _NewID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "NewID");
   private static final QName _ManageNameIDResponse_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "ManageNameIDResponse");
   private static final QName _SessionIndex_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "SessionIndex");
   private static final QName _LogoutResponse_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:protocol", "LogoutResponse");

   public ObjectFactory() {
   }

   public AttributeQuery createAttributeQuery() {
      return new AttributeQuery();
   }

   public ExtensionsType createExtensionsType() {
      return new ExtensionsType();
   }

   public Response createResponse() {
      return new Response();
   }

   public StatusResponseType createStatusResponseType() {
      return new StatusResponseType();
   }

   public Status createStatus() {
      return new Status();
   }

   public StatusCode createStatusCode() {
      return new StatusCode();
   }

   public StatusDetail createStatusDetail() {
      return new StatusDetail();
   }

   public AssertionIDRequest createAssertionIDRequest() {
      return new AssertionIDRequest();
   }

   public AuthnQuery createAuthnQuery() {
      return new AuthnQuery();
   }

   public RequestedAuthnContext createRequestedAuthnContext() {
      return new RequestedAuthnContext();
   }

   public AuthzDecisionQuery createAuthzDecisionQuery() {
      return new AuthzDecisionQuery();
   }

   public AuthnRequest createAuthnRequest() {
      return new AuthnRequest();
   }

   public NameIDPolicy createNameIDPolicy() {
      return new NameIDPolicy();
   }

   public Scoping createScoping() {
      return new Scoping();
   }

   public IDPList createIDPList() {
      return new IDPList();
   }

   public IDPEntry createIDPEntry() {
      return new IDPEntry();
   }

   public ArtifactResolve createArtifactResolve() {
      return new ArtifactResolve();
   }

   public ArtifactResponse createArtifactResponse() {
      return new ArtifactResponse();
   }

   public ManageNameIDRequest createManageNameIDRequest() {
      return new ManageNameIDRequest();
   }

   public Terminate createTerminate() {
      return new Terminate();
   }

   public LogoutRequest createLogoutRequest() {
      return new LogoutRequest();
   }

   public NameIDMappingRequest createNameIDMappingRequest() {
      return new NameIDMappingRequest();
   }

   public NameIDMappingResponse createNameIDMappingResponse() {
      return new NameIDMappingResponse();
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "Extensions"
   )
   public JAXBElement<ExtensionsType> createExtensions(ExtensionsType value) {
      return new JAXBElement(_Extensions_QNAME, ExtensionsType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "StatusMessage"
   )
   public JAXBElement<String> createStatusMessage(String value) {
      return new JAXBElement(_StatusMessage_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "SubjectQuery"
   )
   public JAXBElement<SubjectQueryAbstractType> createSubjectQuery(SubjectQueryAbstractType value) {
      return new JAXBElement(_SubjectQuery_QNAME, SubjectQueryAbstractType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "GetComplete"
   )
   public JAXBElement<String> createGetComplete(String value) {
      return new JAXBElement(_GetComplete_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "RequesterID"
   )
   public JAXBElement<String> createRequesterID(String value) {
      return new JAXBElement(_RequesterID_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "Artifact"
   )
   public JAXBElement<String> createArtifact(String value) {
      return new JAXBElement(_Artifact_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "NewEncryptedID"
   )
   public JAXBElement<EncryptedElementType> createNewEncryptedID(EncryptedElementType value) {
      return new JAXBElement(_NewEncryptedID_QNAME, EncryptedElementType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "NewID"
   )
   public JAXBElement<String> createNewID(String value) {
      return new JAXBElement(_NewID_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "ManageNameIDResponse"
   )
   public JAXBElement<StatusResponseType> createManageNameIDResponse(StatusResponseType value) {
      return new JAXBElement(_ManageNameIDResponse_QNAME, StatusResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "SessionIndex"
   )
   public JAXBElement<String> createSessionIndex(String value) {
      return new JAXBElement(_SessionIndex_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      name = "LogoutResponse"
   )
   public JAXBElement<StatusResponseType> createLogoutResponse(StatusResponseType value) {
      return new JAXBElement(_LogoutResponse_QNAME, StatusResponseType.class, (Class)null, value);
   }
}
