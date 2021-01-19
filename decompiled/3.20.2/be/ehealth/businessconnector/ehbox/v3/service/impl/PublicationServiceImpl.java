package be.ehealth.businessconnector.ehbox.v3.service.impl;

import be.ehealth.businessconnector.ehbox.v3.service.EhBoxServiceHelper;
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
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;
import java.net.MalformedURLException;
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
         return (SendMessageResponse)EhBoxServiceHelper.callEhBoxService(token, ServiceFactory.getPublicationService(token), "urn:be:fgov:ehealth:ehbox:publication:protocol:v3:sendMessage", request, SendMessageResponse.class, this.sessionValidator, this.replyValidator);
      } catch (MalformedURLException var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var4, new Object[]{var4.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendMessageRequest.class);
      JaxbContextFactory.initJaxbContext(SendMessageResponse.class);
   }
}
