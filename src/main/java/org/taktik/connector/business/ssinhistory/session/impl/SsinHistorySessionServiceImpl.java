package org.taktik.connector.business.ssinhistory.session.impl;

import org.taktik.connector.business.ssinhistory.service.SsinHistoryTokenService;
import org.taktik.connector.business.ssinhistory.service.impl.SsinHistoryTokenServiceImpl;
import org.taktik.connector.business.ssinhistory.session.SsinHistorySessionService;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
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
