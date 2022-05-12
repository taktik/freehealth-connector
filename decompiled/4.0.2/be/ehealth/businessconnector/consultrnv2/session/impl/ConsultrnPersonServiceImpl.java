package be.ehealth.businessconnector.consultrnv2.session.impl;

import be.ehealth.businessconnector.consultrnv2.session.ConsultrnPersonService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse;

public class ConsultrnPersonServiceImpl implements ConsultrnPersonService {
   private be.ehealth.businessconnector.consultrnv2.service.ConsultrnPersonService service;

   public ConsultrnPersonServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.service = new be.ehealth.businessconnector.consultrnv2.service.impl.ConsultrnPersonServiceImpl(sessionValidator, replyValidator);
   }

   public SearchPersonBySsinResponse searchPersonBySsin(SearchPersonBySsinRequest request) throws TechnicalConnectorException {
      Session.validateSession();
      return this.service.searchPersonBySsin(Session.getSAMLToken(), request);
   }

   public SearchPersonPhoneticallyResponse searchPersonPhonetically(SearchPersonPhoneticallyRequest request) throws TechnicalConnectorException {
      Session.validateSession();
      return this.service.searchPersonPhonetically(Session.getSAMLToken(), request);
   }
}
