package be.ehealth.businessconnector.ssinhistory.session.impl;

import be.ehealth.businessconnector.ssinhistory.service.SsinHistoryTokenService;
import be.ehealth.businessconnector.ssinhistory.service.impl.SsinHistoryTokenServiceImpl;
import be.ehealth.businessconnector.ssinhistory.session.SsinHistorySessionService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsResponse;

public class SsinHistorySessionServiceImpl implements SsinHistorySessionService {
   private SsinHistoryTokenService service;

   public SsinHistorySessionServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.service = new SsinHistoryTokenServiceImpl(sessionValidator, replyValidator);
   }

   public ConsultRelatedSsinsResponse consultRelatedSsins(ConsultRelatedSsinsRequest request) throws TechnicalConnectorException {
      return this.service.consultRelatedSsins(getSAMLToken(), request);
   }

   public ConsultCurrentSsinResponse consultCurrentSsinResponse(ConsultCurrentSsinRequest request) throws TechnicalConnectorException {
      return this.service.consultCurrentSsin(getSAMLToken(), request);
   }

   private static SAMLToken getSAMLToken() throws TechnicalConnectorException {
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      } else {
         return Session.getInstance().getSession().getSAMLToken();
      }
   }
}
