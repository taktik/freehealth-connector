package org.taktik.connector.business.dicsv3.service;

import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;

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
         genReq.setDefaultHandlerChain();
         genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.dicsv3", "/ehealth-dics/XSD/ehealth-dics-protocol-3_0.xsd"));
         return genReq;
      }
   }
}
