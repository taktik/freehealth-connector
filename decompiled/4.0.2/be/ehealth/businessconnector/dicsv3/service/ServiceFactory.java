package be.ehealth.businessconnector.dicsv3.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_DICS_V3 = "endpoint.dicsv3";
   private static final String PROP_VALIDATION_INCOMING_DICS = "validation.incoming.message.dicsv3";
   public static final String DICS_XSD = "/ehealth-dics/XSD/ehealth-dics-protocol-3_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
      throw new UnsupportedOperationException("class may not be initialized, only static methods should be used");
   }

   public static GenericRequest getDicsService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      if (token == null) {
         throw new IllegalArgumentException("getDicsService : missing input parameter token");
      } else if (soapAction == null) {
         throw new IllegalArgumentException("getDicsService :missing input parameter soapAction");
      } else {
         GenericRequest genReq = new GenericRequest();
         genReq.setEndpoint(config.getProperty("endpoint.dicsv3", "$uddi{uddi:ehealth-fgov-be:business:dics:v3}"));
         genReq.setSoapAction(soapAction);
         genReq.setCredential(token, TokenType.X509);
         genReq.addDefaulHandlerChain();
         genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.dicsv3", "/ehealth-dics/XSD/ehealth-dics-protocol-3_0.xsd"));
         return genReq;
      }
   }
}
