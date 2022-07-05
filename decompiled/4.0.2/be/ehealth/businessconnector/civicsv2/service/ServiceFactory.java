package be.ehealth.businessconnector.civicsv2.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_CIVICS_V2 = "endpoint.civicsv2";
   private static final String PROP_VALIDATION_INCOMING_CIVICSV2 = "validation.incoming.message.civicsv2";
   public static final String CIVICS_XSD = "/ehealth-civics/XSD/sam-civics-protocol-2_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
      throw new UnsupportedOperationException("class may not be initialized, only static methods should be used");
   }

   public static GenericRequest getCivicsService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      if (token == null) {
         throw new IllegalArgumentException("getCivicsService : missing input parameter token");
      } else if (soapAction == null) {
         throw new IllegalArgumentException("getCivicsService :missing input parameter soapAction");
      } else {
         GenericRequest genReq = new GenericRequest();
         genReq.setEndpoint(config.getProperty("endpoint.civicsv2", "$uddi{uddi:ehealth-fgov-be:business:samcivics:v2}"));
         genReq.setSoapAction(soapAction);
         genReq.setCredential(token, TokenType.X509);
         genReq.addDefaulHandlerChain();
         genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.civicsv2", "/ehealth-civics/XSD/sam-civics-protocol-2_0.xsd"));
         return genReq;
      }
   }
}
