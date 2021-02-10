package be.ehealth.businessconnector.consultrn.session.impl;

import be.ehealth.businessconnector.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException;
import be.ehealth.businessconnector.consultrn.exception.manageperson.ConsultrnRegisterExistingPersonException;
import be.ehealth.businessconnector.consultrn.exception.manageperson.ConsultrnRegisterPersonException;
import be.ehealth.businessconnector.consultrn.exception.phoneticsearch.ConsultrnPhoneticSearchException;
import be.ehealth.businessconnector.consultrn.service.impl.ConsultrnServiceImpl;
import be.ehealth.businessconnector.consultrn.session.ConsultrnService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINRequest;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse;

public class ConsultrnSessionServiceImpl implements ConsultrnService {
   private be.ehealth.businessconnector.consultrn.service.ConsultrnService consultrnService;

   public ConsultrnSessionServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.consultrnService = new ConsultrnServiceImpl(sessionValidator, replyValidator);
   }

   public SearchBySSINReply search(SearchBySSINRequest request) throws ConsultrnIdentifyPersonException, TechnicalConnectorException {
      Session.validateSession();
      return this.consultrnService.search(Session.getSAMLToken(), request);
   }

   public SearchPhoneticReply search(SearchPhoneticRequest request) throws ConsultrnPhoneticSearchException, TechnicalConnectorException {
      Session.validateSession();
      return this.consultrnService.search(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public RegisterPersonResponse registerPerson(RegisterPersonRequest request) throws ConsultrnRegisterPersonException, TechnicalConnectorException, ConsultrnRegisterExistingPersonException {
      Session.validateSession();
      return this.consultrnService.registerPerson(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
