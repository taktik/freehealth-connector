package be.ehealth.businessconnector.mediprima.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import org.apache.commons.lang.Validate;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_CONSULTATION_MEDIPRIMA = "endpoint.mediprima.consultation";
   private static final String PROP_VALIDATION_INCOMING_MEDIPRIMA = "validation.incoming.message.mediprima.consultation";
   private static final String PROP_VALIDATION_INCOMING_CONS_TARIFICATION_MEDIPRIMA = "validation.incoming.message.mediprima.tarification";
   private static final String PROP_ENDPOINT_TARIFICATION_MEDIPRIMA = "endpoint.mediprima.tarification";
   protected static final String TARIFICATION_PROTOCOL = "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd";
   public static final String MEDIPRIMA_XSD = "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-1_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
      throw new UnsupportedOperationException("class may not be initialized, only static methods should be used");
   }

   public static GenericRequest getMediprimaTarificationService(SAMLToken token) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.mediprima.tarification", "$uddi{uddi:ehealth-fgov-be:business:mycarenettarificationmediprima:v1}")).setCredential(token, TokenType.SAML).setSoapAction("urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1:TarificationConsult").addDefaulHandlerChain().addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mediprima.tarification", "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd"));
   }

   public static GenericRequest getMediprimaConsultationService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      Validate.notNull(token, "Required parameter soapAction is null.");
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.mediprima.consultation", "$uddi{uddi:ehealth-fgov-be:business:mediprimaconsult:v1}")).setCredential(token, TokenType.SAML).setSoapAction(soapAction).addDefaulHandlerChain().addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mediprima.consultation", "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-1_0.xsd"));
   }
}
