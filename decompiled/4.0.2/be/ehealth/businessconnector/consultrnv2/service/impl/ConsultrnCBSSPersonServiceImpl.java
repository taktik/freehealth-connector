package be.ehealth.businessconnector.consultrnv2.service.impl;

import be.ehealth.businessconnector.consultrnv2.service.ConsultrnCBSSPersonService;
import be.ehealth.businessconnector.consultrnv2.service.ServiceFactory;
import be.ehealth.businessconnector.consultrnv2.service.impl.support.ConsultrnService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse;

public class ConsultrnCBSSPersonServiceImpl implements ConsultrnCBSSPersonService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String SOAP_ACTION_REGISTER_PERSON = "urn:be:fgov:ehealth:rn:cbsspersonservice:protocol:v1:registerPerson";
   private ConsultrnService service;

   public ConsultrnCBSSPersonServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.service = new ConsultrnService(sessionValidator, replyValidator);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(RegisterPersonRequest.class);
      JaxbContextFactory.initJaxbContext(RegisterPersonResponse.class);
   }

   public RegisterPersonResponse registerPerson(SAMLToken token, RegisterPersonRequest request) throws TechnicalConnectorException {
      return (RegisterPersonResponse)this.service.doOperation(this.getService(token, "urn:be:fgov:ehealth:rn:cbsspersonservice:protocol:v1:registerPerson"), token, request, "urn:be:fgov:ehealth:rn:cbsspersonservice:protocol:v1:registerPerson", RegisterPersonResponse.class);
   }

   private GenericRequest getService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      return ServiceFactory.getConsultrnCBSSPersonService(token, soapAction);
   }
}
