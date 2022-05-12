package be.ehealth.businessconnector.ssinhistory.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;

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
      genReq.addDefaulHandlerChain();
      genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.ssinhistory", "/ehealth-consultrn-ssinhistory/XSD/ehealth-ssinhistory-protocol-1_0.xsd"));
      return genReq;
   }
}
