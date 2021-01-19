package org.taktik.connector.business.consultrnv2.service.impl;

import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse;
import org.taktik.connector.business.consultrnv2.service.ConsultrnCBSSPersonService;
import org.taktik.connector.business.consultrnv2.service.ServiceFactory;
import org.taktik.connector.business.consultrnv2.service.impl.support.ConsultrnService;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;

public class ConsultrnCBSSPersonServiceImpl implements ConsultrnCBSSPersonService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String SOAP_ACTION_REGISTER_PERSON = "urn:be:fgov:ehealth:rn:cbsspersonservice:protocol:v1:registerPerson";
   private ConsultrnService service;

   public ConsultrnCBSSPersonServiceImpl(EhealthReplyValidator replyValidator) {
      this.service = new ConsultrnService(replyValidator);
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
