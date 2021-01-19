package be.ehealth.businessconnector.consultrnv2.service;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import org.apache.commons.lang.Validate;

public class ServiceFactory {
   private static final String PROP_VALIDATION_INCOMING_CONSULTRN = "validation.incoming.message.consultrnv2";
   private static final String PROP_ENDPOINT_CONSULTRN_V2_PERSON_SERVICE = "endpoint.consultrnv2.personservice";
   private static final String PROP_ENDPOINT_CONSULTRN_V2_CBSS_PERSON_SERVICE = "endpoint.consultrnv2.cbsspersonservice";
   public static final String CONSULTRN_V2_PERSON_SERVICE_XSD = "/ehealth-rnconsult-person/XSD/ehealth-rnconsult-personservice-protocol-1_0.xsd";
   public static final String CONSULTRN_V2_CBSS_PERSON_SERVICE_XSD = "/ehealth-rnconsult-cbssperson/XSD/ehealth-rnconsult-cbsspersonservice-protocol-1_0.xsd";
   private static final String CONSULTRN_V2_PERSON_SERVICE_UDDI = "$uddi{uddi:ehealth-fgov-be:business:rnconsult:personservice:v1}";
   private static final String CONSULTRN_V2_CBSS_PERSON_SERVICE_UDDI = "$uddi{uddi:ehealth-fgov-be:business:rnconsult:cbsspersonservice:v1}";
   private static Configuration config = ConfigFactory.getConfigValidator();

   public static GenericRequest getConsultrnPersonService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      return getConsultrnService(token, soapAction, "/ehealth-rnconsult-person/XSD/ehealth-rnconsult-personservice-protocol-1_0.xsd", "endpoint.consultrnv2.personservice", "$uddi{uddi:ehealth-fgov-be:business:rnconsult:personservice:v1}");
   }

   public static GenericRequest getConsultrnCBSSPersonService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      return getConsultrnService(token, soapAction, "/ehealth-rnconsult-cbssperson/XSD/ehealth-rnconsult-cbsspersonservice-protocol-1_0.xsd", "endpoint.consultrnv2.cbsspersonservice", "$uddi{uddi:ehealth-fgov-be:business:rnconsult:cbsspersonservice:v1}");
   }

   private static GenericRequest getConsultrnService(SAMLToken token, String soapAction, String xsd, String endpointProp, String uddi) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAML token is null.");
      Validate.notEmpty(soapAction, "Required parameter SOAP action is null or empty");
      return (new GenericRequest()).setEndpoint(config.getProperty(endpointProp, uddi)).setCredential(token, TokenType.SAML).setSoapAction(soapAction).addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.consultrnv2", xsd));
   }
}
