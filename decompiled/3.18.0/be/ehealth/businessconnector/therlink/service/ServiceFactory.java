package be.ehealth.businessconnector.therlink.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.service.impl.TherLinkServiceImpl;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import java.net.MalformedURLException;

public final class ServiceFactory {
   protected static final String THERLINK_PROTOCOL = "/ehealth-hubservices/XSD/hubservices_protocol-2_3.xsd";
   private static final String PROP_ENDPOINT_THERLINK = "endpoint.therlink";
   private static final String PROP_VALIDATION_INCOMING_THERLINK = "validation.incoming.therlink.message";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
   }

   public static GenericRequest getTherLinkPort(SAMLToken token) throws MalformedURLException, TechnicalConnectorException, TherLinkBusinessConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.therlink", "$uddi{uddi:ehealth-fgov-be:business:therlink:v1}"));
      genReq.setCredential(token, TokenType.SAML);
      genReq.setDefaultHandlerChain();
      genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.therlink.message", "/ehealth-hubservices/XSD/hubservices_protocol-2_3.xsd"));
      return genReq;
   }

   public static TherLinkService getTherLinkService() {
      return new TherLinkServiceImpl();
   }
}
