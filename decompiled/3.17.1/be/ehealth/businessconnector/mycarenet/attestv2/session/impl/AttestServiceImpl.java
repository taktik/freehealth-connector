package be.ehealth.businessconnector.mycarenet.attestv2.session.impl;

import be.ehealth.businessconnector.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import be.ehealth.businessconnector.mycarenet.attestv2.session.AttestService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;

public class AttestServiceImpl implements AttestService {
   private be.ehealth.businessconnector.mycarenet.attestv2.service.AttestService service;
   private SessionValidator sessionValidator;

   public AttestServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.service = new be.ehealth.businessconnector.mycarenet.attestv2.service.impl.AttestServiceImpl(sessionValidator, replyValidator);
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
