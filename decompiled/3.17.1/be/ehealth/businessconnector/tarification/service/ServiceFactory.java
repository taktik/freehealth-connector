package be.ehealth.businessconnector.tarification.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_TARIFICATION_MYCARENET_V1 = "endpoint.mcn.tarification";
   private static final String PROP_VALIDATION_INCOMING_CONS_TARIFICATION_MYCARENET = "validation.incoming.message.mcn.tarification";
   protected static final String TARIFICATION_PROT = "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
   }

   /** @deprecated */
   @Deprecated
   public static GenericRequest getTarificationService(SAMLToken token) throws TechnicalConnectorException {
      return getTarificationSessionForMycarenet(token);
   }

   public static GenericRequest getTarificationSessionForMycarenet(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint(config.getProperty("endpoint.mcn.tarification", "$uddi{uddi:ehealth-fgov-be:business:mycarenettarification:v1}"));
      genReq.setSoapAction("urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1:TarificationConsult");
      genReq.setCredential(token, TokenType.SAML);
      genReq.setDefaultHandlerChain();
      genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mcn.tarification", "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd"));
      return genReq;
   }
}
