package org.taktik.connector.business.tarification.service;

import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_TARIFICATION_MYCARENET_V1 = "endpoint.mcn.tarification";
   private static final String PROP_VALIDATION_INCOMING_CONS_TARIFICATION_MYCARENET = "validation.incoming.message.mcn.tarification";
   private static final String PROP_ENDPOINT_TARIFICATION_MEDIPRIMA_V1 = "endpoint.mcn.tarificationmediprima";
   private static final String PROP_VALIDATION_INCOMING_CONS_TARIFICATION_MEDIPRIMA = "validation.incoming.message.mcn.tarificationmediprima";
   protected static final String TARIFICATION_PROT = "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   /** @deprecated */
   @Deprecated
   public static GenericRequest getTarificationService(SAMLToken token) throws TechnicalConnectorException {
      return getTarificationSessionForMycarenet(token);
   }

   public static GenericRequest getTarificationSessionForMediPrima(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest genReq = new GenericRequest();
      genReq.setEndpoint("https://services-acpt.ehealth.fgov.be/beta/MyCareNet/TarificationMediPrima/v1");
      genReq.setSoapAction("urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1:TarificationConsult");
      genReq.setCredential(token, TokenType.SAML);
      genReq.setDefaultHandlerChain();
      genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mcn.tarificationmediprima", "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd"));
      return genReq;
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
