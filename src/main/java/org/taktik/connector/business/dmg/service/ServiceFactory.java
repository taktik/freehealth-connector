package org.taktik.connector.business.dmg.service;

import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.handler.LoggingHandler;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.HandlerChain;
import org.taktik.connector.technical.ws.domain.HandlerPosition;
import org.taktik.connector.technical.ws.domain.TokenType;
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
   static final String[] DMG_XSD = new String[]{"/ehealth-gmf/XSD/gmf_services_protocol-1_1.xsd"};

   public static GenericRequest getConsultationService(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.dmg.consultation.v1", "$uddi{uddi:ehealth-fgov-be:business:globalmedicalfileconsultation:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.setDefaultHandlerChain();
      HandlerChain chain = HandlerChainUtil.buildChainWithValidator("validation.incoming.message.dmg.consultation.v1", DMG_XSD);
      chain.register(HandlerPosition.BEFORE, new LoggingHandler());
      genReq.setHandlerChain(chain);
      return genReq;
   }

   public static GenericRequest getNotificationService(SAMLToken token) throws TechnicalConnectorException {
      LOG.debug("getNotificationService : creating service");
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.dmg.notification.v1", "$uddi{uddi:ehealth-fgov-be:business:globalmedicalfilenotification:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.setDefaultHandlerChain();
      genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.dmg.notification.v1", DMG_XSD));
      return genReq;
   }
}
