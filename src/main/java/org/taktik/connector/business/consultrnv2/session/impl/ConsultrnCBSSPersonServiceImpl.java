package org.taktik.connector.business.consultrnv2.session.impl;

import be.ehealth.businessconnector.consultrnv2.session.ConsultrnCBSSPersonService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse;

public class ConsultrnCBSSPersonServiceImpl implements ConsultrnCBSSPersonService {
   private be.ehealth.businessconnector.consultrnv2.service.ConsultrnCBSSPersonService service;

   public ConsultrnCBSSPersonServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.service = new be.ehealth.businessconnector.consultrnv2.service.impl.ConsultrnCBSSPersonServiceImpl(sessionValidator, replyValidator);
   }

   public RegisterPersonResponse registerPerson(RegisterPersonRequest request) throws TechnicalConnectorException {
      Session.validateSession();
      return this.service.registerPerson(Session.getSAMLToken(), request);
   }
}
