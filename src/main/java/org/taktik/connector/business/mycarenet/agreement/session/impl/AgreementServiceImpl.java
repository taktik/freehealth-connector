package org.taktik.connector.business.mycarenet.agreement.session.impl;

import be.ehealth.businessconnector.mycarenet.agreement.session.AgreementService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementResponse;

public class AgreementServiceImpl implements AgreementService {
   private final be.ehealth.businessconnector.mycarenet.agreement.service.AgreementService service;
   private final SessionValidator sessionValidator;

   public AgreementServiceImpl(SessionValidator sessionValidator) throws TechnicalConnectorException {
      this.service = new be.ehealth.businessconnector.mycarenet.agreement.service.impl.AgreementServiceImpl(sessionValidator);
      this.sessionValidator = sessionValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public AskAgreementResponse askAgreement(AskAgreementRequest request) throws TechnicalConnectorException {
      this.sessionValidator.validateSession();
      return this.service.askAgreement(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public ConsultAgreementResponse consultAgreement(ConsultAgreementRequest request) throws TechnicalConnectorException {
      this.sessionValidator.validateSession();
      return this.service.consultAgreement(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
