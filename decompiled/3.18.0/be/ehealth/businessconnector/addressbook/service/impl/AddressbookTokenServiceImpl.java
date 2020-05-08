package be.ehealth.businessconnector.addressbook.service.impl;

import be.ehealth.businessconnector.addressbook.service.AddressbookTokenService;
import be.ehealth.businessconnector.addressbook.service.TokenServiceFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoResponse;
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoRequest;
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoResponse;
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsRequest;
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsResponse;
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsRequest;
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsResponse;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.util.HashMap;
import java.util.Map;
import javax.xml.soap.SOAPException;

public class AddressbookTokenServiceImpl implements AddressbookTokenService {
   private SessionValidator sessionValidator;
   private EhealthReplyValidator ehealthReplyValidator;
   private static Map<Class<? extends StatusResponseType>, String> soapActions = new HashMap();

   public AddressbookTokenServiceImpl(SessionValidator sessVal, EhealthReplyValidator ehRepVal) throws TechnicalConnectorException {
      this.sessionValidator = sessVal;
      this.ehealthReplyValidator = ehRepVal;
   }

   public GetOrganizationContactInfoResponse getOrganizationContactInfo(SAMLToken token, GetOrganizationContactInfoRequest request) throws TechnicalConnectorException {
      return (GetOrganizationContactInfoResponse)this.invoke(token, request, GetOrganizationContactInfoResponse.class);
   }

   public GetProfessionalContactInfoResponse getProfessionalContactInfo(SAMLToken token, GetProfessionalContactInfoRequest request) throws TechnicalConnectorException {
      return (GetProfessionalContactInfoResponse)this.invoke(token, request, GetProfessionalContactInfoResponse.class);
   }

   public SearchOrganizationsResponse searchOrganizations(SAMLToken token, SearchOrganizationsRequest request) throws TechnicalConnectorException {
      return (SearchOrganizationsResponse)this.invoke(token, request, SearchOrganizationsResponse.class);
   }

   public SearchProfessionalsResponse searchProfessionals(SAMLToken token, SearchProfessionalsRequest request) throws TechnicalConnectorException {
      return (SearchProfessionalsResponse)this.invoke(token, request, SearchProfessionalsResponse.class);
   }

   private <T extends StatusResponseType> T invoke(SAMLToken token, RequestType request, Class<T> clazz) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateToken(token);
         GenericRequest service = TokenServiceFactory.getService(token);
         service.setPayload((Object)request);
         service.setSoapAction((String)soapActions.get(clazz));
         T response = (StatusResponseType)ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
         this.ehealthReplyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
      }
   }

   static {
      soapActions.put(GetProfessionalContactInfoResponse.class, "urn:be:fgov:ehealth:addressbook:protocol:v1:getProfessionalContactInfo");
      soapActions.put(GetOrganizationContactInfoResponse.class, "urn:be:fgov:ehealth:addressbook:protocol:v1:getOrganizationContactInfo");
      soapActions.put(SearchProfessionalsResponse.class, "urn:be:fgov:ehealth:addressbook:protocol:v1:searchProfessionals");
      soapActions.put(SearchOrganizationsResponse.class, "urn:be:fgov:ehealth:addressbook:protocol:v1:searchOrganizations");
   }
}
