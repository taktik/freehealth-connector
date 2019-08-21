package org.taktik.connector.business.consultrn.service;

import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_CONSULTRN_IDENTIFYPERSON = "endpoint.consultrn.identifyperson";
   private static final String PROP_ENDPOINT_CONSULTRN_PHONETICSEARCH = "endpoint.consultrn.phoneticsearch";
   private static final String PROP_ENDPOINT_CONSULTRN_MANAGEPERSON = "endpoint.consultrn.manageperson";
   private static final String PROP_VALIDATION_INCOMING_CONSULTRN = "validation.incoming.message.consultrn";
   public static final String CONSULTRN_XSD = "/ehealth-consultrn-webservices/XSD/ehealth-consultrn-webservices-protocol-1_0.xsd";
   public static final String CONSULTRN_XSD_v2 = "/ehealth-consultrn-webservices/XSD/ehealth-consultrn-manageperson-protocol-2_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
   }

   public static GenericRequest getConsultrnIdentifyPersonService(SAMLToken token) throws TechnicalConnectorException {
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.consultrn.identifyperson", "$uddi{uddi:ehealth-fgov-be:business:consultrnsamlhokidentifyperson:v1}")).setCredential(token, TokenType.SAML).addDefaulHandlerChain().addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.consultrn", "/ehealth-consultrn-webservices/XSD/ehealth-consultrn-webservices-protocol-1_0.xsd"));
   }

   public static GenericRequest getConsultrnPhoneticSearchService(SAMLToken token) throws TechnicalConnectorException {
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.consultrn.phoneticsearch", "$uddi{uddi:ehealth-fgov-be:business:consultrnsamlhokphoneticsearch:v1}")).setCredential(token, TokenType.SAML).addDefaulHandlerChain().addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.consultrn", "/ehealth-consultrn-webservices/XSD/ehealth-consultrn-webservices-protocol-1_0.xsd"));
   }

   public static GenericRequest getConsultrnManagePersonService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.consultrn.manageperson", "$uddi{uddi:ehealth-fgov-be:business:consultrnsamlhokmanageperson:v2}")).setSoapAction(soapAction).setCredential(token, TokenType.SAML).addDefaulHandlerChain().addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.consultrn", "/ehealth-consultrn-webservices/XSD/ehealth-consultrn-manageperson-protocol-2_0.xsd"));
   }
}
