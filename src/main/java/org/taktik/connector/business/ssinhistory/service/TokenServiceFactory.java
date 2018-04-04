package org.taktik.connector.business.ssinhistory.service;

import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;

public final class TokenServiceFactory {
   private static final String PROP_ENDPOINT_SSINHISTORY = "endpoint.ssinhistory";
   private static final String PROP_VALIDATION_INCOMING_SSINHISTORY = "validation.incoming.message.ssinhistory";
   private static final String SSINHISTORY_XSD = "/ehealth-consultrn-ssinhistory/XSD/ehealth-ssinhistory-protocol-1_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private TokenServiceFactory() {
      throw new UnsupportedOperationException("This factory should never be instantiated, only its static methods should be used");
   }

   public static GenericRequest getService(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.ssinhistory", "$uddi{uddi:ehealth-fgov-be:business:ssinhistory:v1}"));
      genReq.setCredential(token, TokenType.X509);
      genReq.setDefaultHandlerChain();
      genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.ssinhistory", "/ehealth-consultrn-ssinhistory/XSD/ehealth-ssinhistory-protocol-1_0.xsd"));
      return genReq;
   }
}
