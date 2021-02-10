package be.ehealth.businessconnector.mycarenet.attest.session.impl;

import be.ehealth.businessconnector.mycarenet.attest.exception.AttestBusinessConnectorException;
import be.ehealth.businessconnector.mycarenet.attest.session.AttestService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationResponse;

public class AttestServiceImpl implements AttestService {
   private be.ehealth.businessconnector.mycarenet.attest.service.AttestService service;

   public AttestServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.service = new be.ehealth.businessconnector.mycarenet.attest.service.impl.AttestServiceImpl(sessionValidator, replyValidator);
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public SendAttestationResponse sendAttestation(SendAttestationRequest request) throws AttestBusinessConnectorException, TechnicalConnectorException {
      return this.service.sendAttestion(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
