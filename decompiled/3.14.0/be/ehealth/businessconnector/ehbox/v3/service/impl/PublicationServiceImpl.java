package be.ehealth.businessconnector.ehbox.v3.service.impl;

import be.ehealth.businessconnector.ehbox.v3.exception.OoOPublicationException;
import be.ehealth.businessconnector.ehbox.v3.service.PublicationService;
import be.ehealth.businessconnector.ehbox.v3.service.ServiceFactory;
import be.ehealth.businessconnector.ehbox.v3.validator.EhboxReplyValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicationServiceImpl implements PublicationService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private EhboxReplyValidator replyValidator;
   private SessionValidator sessionValidator;
   private static final Logger LOG = LoggerFactory.getLogger(PublicationServiceImpl.class);

   public PublicationServiceImpl(SessionValidator sessionValidator, EhboxReplyValidator replyValidator) {
      this.replyValidator = replyValidator;
      this.sessionValidator = sessionValidator;
   }

   public PublicationServiceImpl() {
      LOG.debug("creating PublicationServiceImpl for bootstrap purposes");
   }

   public final SendMessageResponse sendMessage(SAMLToken token, SendMessageRequest request) throws ConnectorException {
      try {
         this.sessionValidator.validateToken(token);
         GenericRequest service = ServiceFactory.getPublicationService(token);
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:ehbox:publication:protocol:v3:sendMessage");
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         SendMessageResponse response = (SendMessageResponse)xmlResponse.asObject(SendMessageResponse.class);
         this.replyValidator.validateReplyStatus(response);
         return response;
      } catch (Exception var6) {
         if (!(var6 instanceof OoOPublicationException) && !(var6 instanceof TechnicalConnectorException)) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
         } else {
            throw (ConnectorException)var6;
         }
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendMessageRequest.class);
      JaxbContextFactory.initJaxbContext(SendMessageResponse.class);
   }
}
