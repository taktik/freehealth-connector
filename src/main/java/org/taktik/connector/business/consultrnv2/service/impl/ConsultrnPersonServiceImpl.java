package org.taktik.connector.business.consultrnv2.service.impl;

import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse;
import org.taktik.connector.business.consultrnv2.service.ConsultrnPersonService;
import org.taktik.connector.business.consultrnv2.service.ServiceFactory;
import org.taktik.connector.business.consultrnv2.service.impl.support.ConsultrnService;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;

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
