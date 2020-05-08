package be.ehealth.businessconnector.mediprimauma.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_MEDIPRIMAUMA = "endpoint.mediprimauma";
   private static final String PROP_VALIDATION_INCOMING_MEDIPRIMAUMA = "validation.incoming.message.mediprimauma";
   public static final String MEDIPRIMAUMA_XSD = "/ehealth-mediprima-uma/XSD/ehealth-mediprima-uma-protocol-1_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
   }

   public static GenericRequest getMediPrimaUmaService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.mediprimauma", "$uddi{uddi:ehealth-fgov-be:business:mediprimauma:v1}")).setSoapAction(soapAction).setCredential(token, TokenType.SAML).addDefaulHandlerChain().addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mediprimauma", "/ehealth-mediprima-uma/XSD/ehealth-mediprima-uma-protocol-1_0.xsd"));
   }
}
