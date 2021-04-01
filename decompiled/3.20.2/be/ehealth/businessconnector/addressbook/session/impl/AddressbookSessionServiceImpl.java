package be.ehealth.businessconnector.addressbook.session.impl;

import be.ehealth.businessconnector.addressbook.service.AddressbookTokenService;
import be.ehealth.businessconnector.addressbook.service.impl.AddressbookTokenServiceImpl;
import be.ehealth.businessconnector.addressbook.session.AddressbookSessionService;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoResponse;
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoResponse;
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsRequest;
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsResponse;
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsRequest;
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsResponse;

public class AddressbookSessionServiceImpl implements AddressbookSessionService {
   private AddressbookTokenService service;

   public AddressbookSessionServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.service = new AddressbookTokenServiceImpl(sessionValidator, replyValidator);
   }

   public GetOrganizationContactInfoResponse getOrganizationContactInfo(GetOrganizationContactInfoRequest request) throws TechnicalConnectorException {
      return this.service.getOrganizationContactInfo(getSAMLToken(), request);
   }

   public GetProfessionalContactInfoResponse getProfessionalContactInfo(GetProfessionalContactInfoRequest request) throws TechnicalConnectorException {
      return this.service.getProfessionalContactInfo(getSAMLToken(), request);
   }

   public SearchOrganizationsResponse searchOrganizations(SearchOrganizationsRequest request) throws TechnicalConnectorException {
      return this.service.searchOrganizations(getSAMLToken(), request);
   }

   public SearchProfessionalsResponse searchProfessionals(SearchProfessionalsRequest request) throws TechnicalConnectorException {
      return this.service.searchProfessionals(getSAMLToken(), request);
   }

   private static SAMLToken getSAMLToken() throws SessionManagementException, TechnicalConnectorException {
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      } else {
         return Session.getInstance().getSession().getSAMLToken();
      }
   }
}
