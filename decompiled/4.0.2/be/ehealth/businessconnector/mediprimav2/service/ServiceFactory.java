package be.ehealth.businessconnector.mediprimav2.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import org.apache.commons.lang.Validate;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_CONSULTATION_MEDIPRIMA = "endpoint.mediprimav2.consultation";
   private static final String PROP_VALIDATION_INCOMING_MEDIPRIMA = "validation.incoming.message.mediprimav2.consultation";
   public static final String MEDIPRIMA_XSD = "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-2_0.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   private ServiceFactory() {
      throw new UnsupportedOperationException("class may not be initialized, only static methods should be used");
   }

   public static GenericRequest getMediprimaConsultationService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      Validate.notNull(token, "Required parameter soapAction is null.");
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.mediprimav2.consultation", "$uddi{uddi:ehealth-fgov-be:business:mediprimaconsult:v2}")).setCredential(token, TokenType.SAML).setSoapAction(soapAction).addDefaulHandlerChain().addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mediprimav2.consultation", "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-2_0.xsd"));
   }
}
