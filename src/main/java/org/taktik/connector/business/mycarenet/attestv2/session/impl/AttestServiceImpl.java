package org.taktik.connector.business.mycarenet.attestv2.session.impl;

import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import org.taktik.connector.business.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import org.taktik.connector.business.mycarenet.attestv2.session.AttestService;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;

public class AttestServiceImpl implements AttestService {
   private org.taktik.connector.business.mycarenet.attestv2.service.AttestService service;
   private SessionValidator sessionValidator;

   public AttestServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.service = new org.taktik.connector.business.mycarenet.attestv2.service.impl.AttestServiceImpl(sessionValidator, replyValidator);
      this.sessionValidator = sessionValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public SendAttestationResponse sendAttestation(SendRequestType request) throws AttestBusinessConnectorException, TechnicalConnectorException {
      this.sessionValidator.validateSession();
      return this.service.sendAttestion(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public CancelAttestationResponse cancelAttestation(SendRequestType request) throws AttestBusinessConnectorException, TechnicalConnectorException {
      this.sessionValidator.validateSession();
      return this.service.cancelAttestion(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
