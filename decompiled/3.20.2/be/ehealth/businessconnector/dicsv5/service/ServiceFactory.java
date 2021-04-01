package be.ehealth.businessconnector.dicsv5.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import org.apache.commons.lang.Validate;

public class ServiceFactory {
   private static final String PROP_ENDPOINT_DICS_V5 = "endpoint.dicsv5";
   private static final String PROP_VALIDATION_INCOMING_DICS = "validation.incoming.message.dicsv5";
   public static final String DICS_XSD = "/dics/XSD/ehealth-dics-protocol-5_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
   }

   public static GenericRequest getDicsService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAML token is null.");
      Validate.notEmpty(soapAction, "Required parameter SOAP action is null or empty.");
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.dicsv5", "$uddi{uddi:ehealth-fgov-be:business:dics:v5}")).setCredential(token, TokenType.X509).setSoapAction(soapAction).addDefaulHandlerChain().addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.dicsv5", "/dics/XSD/ehealth-dics-protocol-5_0.xsd"));
   }
}
