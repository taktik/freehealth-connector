package org.taktik.connector.business.registration.service;

import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_REGISTRATION_V1 = "endpoint.mcn.registration";
   private static final String PROP_VALIDATION_INCOMING_CONS_REGISTRATION = "validation.incoming.message.mcn.registration";
   protected static final String REGISTRATION_PROT = "/ehealth-mycarenetregistration/XSD/mycarenet-registration-protocol-1_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   public static GenericRequest getRegistrationService(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.mcn.registration", "$uddi{uddi:ehealth-fgov-be:business:mycarenetregistration:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.setDefaultHandlerChain();
      genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mcn.registration", "/ehealth-mycarenetregistration/XSD/mycarenet-registration-protocol-1_0.xsd"));
      return genReq;
   }
}
