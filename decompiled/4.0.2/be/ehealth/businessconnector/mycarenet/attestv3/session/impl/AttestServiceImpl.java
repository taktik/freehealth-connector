package be.ehealth.businessconnector.mycarenet.attestv3.session.impl;

import be.ehealth.businessconnector.mycarenet.attestv3.session.AttestService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;

public class AttestServiceImpl implements AttestService {
   private be.ehealth.businessconnector.mycarenet.attestv3.service.AttestService service;
   private SessionValidator sessionValidator;

   public AttestServiceImpl(SessionValidator sessionValidator) throws TechnicalConnectorException {
      this.service = new be.ehealth.businessconnector.mycarenet.attestv3.service.impl.AttestServiceImpl(sessionValidator);
      this.sessionValidator = sessionValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public SendAttestationResponse sendAttestation(SendRequestType request) throws TechnicalConnectorException {
      this.sessionValidator.validateSession();
      return this.service.sendAttestion(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public CancelAttestationResponse cancelAttestation(SendRequestType request) throws TechnicalConnectorException {
      this.sessionValidator.validateSession();
      return this.service.cancelAttestion(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
