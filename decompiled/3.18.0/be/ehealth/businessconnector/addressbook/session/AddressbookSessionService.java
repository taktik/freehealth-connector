package be.ehealth.businessconnector.addressbook.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoResponse;
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoResponse;
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsRequest;
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsResponse;
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsRequest;
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsResponse;

public interface AddressbookSessionService {
   GetOrganizationContactInfoResponse getOrganizationContactInfo(GetOrganizationContactInfoRequest var1) throws TechnicalConnectorException;

   GetProfessionalContactInfoResponse getProfessionalContactInfo(GetProfessionalContactInfoRequest var1) throws TechnicalConnectorException;

   SearchOrganizationsResponse searchOrganizations(SearchOrganizationsRequest var1) throws TechnicalConnectorException;

   SearchProfessionalsResponse searchProfessionals(SearchProfessionalsRequest var1) throws TechnicalConnectorException;
}
