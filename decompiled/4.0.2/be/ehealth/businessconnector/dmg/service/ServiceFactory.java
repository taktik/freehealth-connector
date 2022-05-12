package be.ehealth.businessconnector.dmg.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.handler.LoggingHandler;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServiceFactory {
   private static final Logger LOG = LoggerFactory.getLogger(ServiceFactory.class);
   private static final String PROP_ENDPOINT_DMG_CONSULTATION_V1 = "endpoint.dmg.consultation.v1";
   private static final String PROP_ENDPOINT_DMG_NOTIFICATION_V1 = "endpoint.dmg.notification.v1";
   private static final String PROP_VALIDATION_INCOMING_CONS_DMG = "validation.incoming.message.dmg.consultation.v1";
   private static final String PROP_VALIDATION_INCOMING_NOT_DMG = "validation.incoming.message.dmg.notification.v1";
   private static final String DMG_PROT = "/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();
   public static final String[] DMG_XSD = new String[]{"/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd"};

   private ServiceFactory() {
   }

   public static GenericRequest getConsultationService(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.dmg.consultation.v1", "$uddi{uddi:ehealth-fgov-be:business:globalmedicalfileconsultation:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      HandlerChain chain = HandlerChainUtil.buildChainWithValidator("validation.incoming.message.dmg.consultation.v1", DMG_XSD);
      chain.register(HandlerPosition.BEFORE, new LoggingHandler());
      genReq.addHandlerChain(chain);
      return genReq;
   }

   public static GenericRequest getNotificationService(SAMLToken token) throws TechnicalConnectorException {
      LOG.debug("getNotificationService : creating service");
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.dmg.notification.v1", "$uddi{uddi:ehealth-fgov-be:business:globalmedicalfilenotification:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.dmg.notification.v1", DMG_XSD));
      return genReq;
   }
}
