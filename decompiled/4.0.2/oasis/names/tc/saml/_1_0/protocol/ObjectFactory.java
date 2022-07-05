package oasis.names.tc.saml._1_0.protocol;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _RespondWith_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:protocol", "RespondWith");
   private static final QName _AssertionArtifact_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:protocol", "AssertionArtifact");
   private static final QName _SubjectQuery_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:protocol", "SubjectQuery");
   private static final QName _Query_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:protocol", "Query");
   private static final QName _StatusMessage_QNAME = new QName("urn:oasis:names:tc:SAML:1.0:protocol", "StatusMessage");

   public ObjectFactory() {
   }

   public Request createRequest() {
      return new Request();
   }

   public AuthorizationDecisionQuery createAuthorizationDecisionQuery() {
      return new AuthorizationDecisionQuery();
   }

   public AttributeQuery createAttributeQuery() {
      return new AttributeQuery();
   }

   public AuthenticationQuery createAuthenticationQuery() {
      return new AuthenticationQuery();
   }

   public Response createResponse() {
      return new Response();
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

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:protocol",
      name = "RespondWith"
   )
   public JAXBElement<QName> createRespondWith(QName value) {
      return new JAXBElement(_RespondWith_QNAME, QName.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:protocol",
      name = "AssertionArtifact"
   )
   public JAXBElement<String> createAssertionArtifact(String value) {
      return new JAXBElement(_AssertionArtifact_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:protocol",
      name = "SubjectQuery"
   )
   public JAXBElement<SubjectQueryAbstractType> createSubjectQuery(SubjectQueryAbstractType value) {
      return new JAXBElement(_SubjectQuery_QNAME, SubjectQueryAbstractType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:protocol",
      name = "Query"
   )
   public JAXBElement<QueryAbstractType> createQuery(QueryAbstractType value) {
      return new JAXBElement(_Query_QNAME, QueryAbstractType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:SAML:1.0:protocol",
      name = "StatusMessage"
   )
   public JAXBElement<String> createStatusMessage(String value) {
      return new JAXBElement(_StatusMessage_QNAME, String.class, (Class)null, value);
   }
}
