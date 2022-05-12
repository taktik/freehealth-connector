package be.ehealth.businessconnector.registration.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_REGISTRATION_V1 = "endpoint.mcn.registration";
   private static final String PROP_VALIDATION_INCOMING_CONS_REGISTRATION = "validation.incoming.message.mcn.registration";
   protected static final String REGISTRATION_PROT = "/ehealth-mycarenetregistration/XSD/mycarenet-registration-protocol-1_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
   }

   public static GenericRequest getRegistrationService(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.mcn.registration", "$uddi{uddi:ehealth-fgov-be:business:mycarenetregistration:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mcn.registration", "/ehealth-mycarenetregistration/XSD/mycarenet-registration-protocol-1_0.xsd"));
      return genReq;
   }
}
