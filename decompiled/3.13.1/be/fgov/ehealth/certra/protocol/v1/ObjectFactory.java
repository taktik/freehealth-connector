package be.fgov.ehealth.certra.protocol.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _OrganizationTypeRequest_QNAME = new QName("urn:be:fgov:ehealth:certra:protocol:v1", "OrganizationTypeRequest");

   public GetEHActorQualitiesResponse createGetEHActorQualitiesResponse() {
      return new GetEHActorQualitiesResponse();
   }

   public RaResponseType createRaResponseType() {
      return new RaResponseType();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public GetEHActorQualitiesRequest createGetEHActorQualitiesRequest() {
      return new GetEHActorQualitiesRequest();
   }

   public GetRevocableCertificatesRequest createGetRevocableCertificatesRequest() {
      return new GetRevocableCertificatesRequest();
   }

   public GetRevocableCertificatesResponse createGetRevocableCertificatesResponse() {
      return new GetRevocableCertificatesResponse();
   }

   public RevokeRequest createRevokeRequest() {
      return new RevokeRequest();
   }

   public RevokeResponse createRevokeResponse() {
      return new RevokeResponse();
   }

   public GenerateCertificateRequest createGenerateCertificateRequest() {
      return new GenerateCertificateRequest();
   }

   public GenerateCertificateResponse createGenerateCertificateResponse() {
      return new GenerateCertificateResponse();
   }

   public GenerateCertificateForRenewalRequest createGenerateCertificateForRenewalRequest() {
      return new GenerateCertificateForRenewalRequest();
   }

   public GenerateCertificateForRenewalResponse createGenerateCertificateForRenewalResponse() {
      return new GenerateCertificateForRenewalResponse();
   }

   public GetCertificateRequest createGetCertificateRequest() {
      return new GetCertificateRequest();
   }

   public GetCertificateResponse createGetCertificateResponse() {
      return new GetCertificateResponse();
   }

   public ValidateRenewRequest createValidateRenewRequest() {
      return new ValidateRenewRequest();
   }

   public ValidateRenewResponse createValidateRenewResponse() {
      return new ValidateRenewResponse();
   }

   public SearchCriteriumType createSearchCriteriumType() {
      return new SearchCriteriumType();
   }

   public ContactDataType createContactDataType() {
      return new ContactDataType();
   }

   public OrganizationTypeResponse createOrganizationTypeResponse() {
      return new OrganizationTypeResponse();
   }

   public GetExistingApplicationIdsRequest createGetExistingApplicationIdsRequest() {
      return new GetExistingApplicationIdsRequest();
   }

   public GetExistingApplicationIdsResponse createGetExistingApplicationIdsResponse() {
      return new GetExistingApplicationIdsResponse();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:certra:protocol:v1",
      name = "OrganizationTypeRequest"
   )
   public JAXBElement<Object> createOrganizationTypeRequest(Object value) {
      return new JAXBElement(_OrganizationTypeRequest_QNAME, Object.class, (Class)null, value);
   }
}
