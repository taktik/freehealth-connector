package be.ehealth.businessconnector.addressbook.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;

public final class TokenServiceFactory {
   private static final String PROP_ENDPOINT_ADDRESSBOOK = "endpoint.addressbook";
   private static final String PROP_VALIDATION_INCOMING_ADDRESSBOOK = "validation.incoming.message.addressbook";
   private static final String ADDRESSBOOK_XSD = "/XSD/ehealth-addressbook/XSD/ehealth-addressbook-protocol-1_1.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private TokenServiceFactory() {
      throw new UnsupportedOperationException("This factory should never be instantiated, only its static methods should be used");
   }

   public static GenericRequest getService(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.addressbook", "$uddi{uddi:ehealth-fgov-be:business:addressbook:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.setDefaultHandlerChain();
      genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.addressbook", "/XSD/ehealth-addressbook/XSD/ehealth-addressbook-protocol-1_1.xsd"));
      return genReq;
   }
}
