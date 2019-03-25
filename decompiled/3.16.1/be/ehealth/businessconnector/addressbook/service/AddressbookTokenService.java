package be.ehealth.businessconnector.addressbook.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoResponse;
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoResponse;
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsRequest;
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsResponse;
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsRequest;
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsResponse;

public interface AddressbookTokenService {
   GetOrganizationContactInfoResponse getOrganizationContactInfo(SAMLToken var1, GetOrganizationContactInfoRequest var2) throws TechnicalConnectorException;

   GetProfessionalContactInfoResponse getProfessionalContactInfo(SAMLToken var1, GetProfessionalContactInfoRequest var2) throws TechnicalConnectorException;

   SearchOrganizationsResponse searchOrganizations(SAMLToken var1, SearchOrganizationsRequest var2) throws TechnicalConnectorException;

   SearchProfessionalsResponse searchProfessionals(SAMLToken var1, SearchProfessionalsRequest var2) throws TechnicalConnectorException;
}
