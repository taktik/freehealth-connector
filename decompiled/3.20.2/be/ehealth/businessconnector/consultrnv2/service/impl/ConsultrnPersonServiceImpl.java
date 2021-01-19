package be.ehealth.businessconnector.consultrnv2.service.impl;

import be.ehealth.businessconnector.consultrnv2.service.ConsultrnPersonService;
import be.ehealth.businessconnector.consultrnv2.service.ServiceFactory;
import be.ehealth.businessconnector.consultrnv2.service.impl.support.ConsultrnService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse;

public class ConsultrnPersonServiceImpl implements ConsultrnPersonService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String SOAP_ACTION_SEARCH_PERSON_BY_SSIN = "urn:be:fgov:ehealth:rn:personservice:protocol:v1:searchPersonBySsin";
   private static final String SOAP_ACTION_SEARCH_PERSON_PHONETICALLY = "urn:be:fgov:ehealth:rn:personservice:protocol:v1:searchPersonPhonetically";
   private ConsultrnService service;

   public ConsultrnPersonServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.service = new ConsultrnService(sessionValidator, replyValidator);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SearchPersonBySsinRequest.class);
      JaxbContextFactory.initJaxbContext(SearchPersonBySsinResponse.class);
      JaxbContextFactory.initJaxbContext(SearchPersonPhoneticallyRequest.class);
      JaxbContextFactory.initJaxbContext(SearchPersonPhoneticallyResponse.class);
   }

   public SearchPersonBySsinResponse searchPersonBySsin(SAMLToken token, SearchPersonBySsinRequest request) throws TechnicalConnectorException {
      return (SearchPersonBySsinResponse)this.service.doOperation(this.getService(token, "urn:be:fgov:ehealth:rn:personservice:protocol:v1:searchPersonBySsin"), token, request, "urn:be:fgov:ehealth:rn:personservice:protocol:v1:searchPersonBySsin", SearchPersonBySsinResponse.class);
   }

   public SearchPersonPhoneticallyResponse searchPersonPhonetically(SAMLToken token, SearchPersonPhoneticallyRequest request) throws TechnicalConnectorException {
      return (SearchPersonPhoneticallyResponse)this.service.doOperation(this.getService(token, "urn:be:fgov:ehealth:rn:personservice:protocol:v1:searchPersonPhonetically"), token, request, "urn:be:fgov:ehealth:rn:personservice:protocol:v1:searchPersonPhonetically", SearchPersonPhoneticallyResponse.class);
   }

   private GenericRequest getService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      return ServiceFactory.getConsultrnPersonService(token, soapAction);
   }
}
