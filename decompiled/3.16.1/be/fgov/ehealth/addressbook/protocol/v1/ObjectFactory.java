package be.fgov.ehealth.addressbook.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public GetOrganizationContactInfoRequest createGetOrganizationContactInfoRequest() {
      return new GetOrganizationContactInfoRequest();
   }

   public GetOrganizationContactInfoResponse createGetOrganizationContactInfoResponse() {
      return new GetOrganizationContactInfoResponse();
   }

   public GetProfessionalContactInfoRequest createGetProfessionalContactInfoRequest() {
      return new GetProfessionalContactInfoRequest();
   }

   public GetProfessionalContactInfoResponse createGetProfessionalContactInfoResponse() {
      return new GetProfessionalContactInfoResponse();
   }

   public SearchOrganizationsRequest createSearchOrganizationsRequest() {
      return new SearchOrganizationsRequest();
   }

   public SearchOrganizationsResponse createSearchOrganizationsResponse() {
      return new SearchOrganizationsResponse();
   }

   public HealthCareOrganization createHealthCareOrganization() {
      return new HealthCareOrganization();
   }

   public SearchProfessionalsRequest createSearchProfessionalsRequest() {
      return new SearchProfessionalsRequest();
   }

   public SearchProfessionalsResponse createSearchProfessionalsResponse() {
      return new SearchProfessionalsResponse();
   }
}
