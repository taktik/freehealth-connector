package org.taktik.connector.business.consultrnv2.service;

import org.apache.commons.lang.Validate;
import org.taktik.connector.business.common.util.HandlerChainUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;

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
      return getConsultrnService(token, soapAction, CONSULTRN_V2_PERSON_SERVICE_XSD, PROP_ENDPOINT_CONSULTRN_V2_PERSON_SERVICE, CONSULTRN_V2_PERSON_SERVICE_UDDI);
   }

   public static GenericRequest getConsultrnCBSSPersonService(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      return getConsultrnService(token, soapAction, CONSULTRN_V2_CBSS_PERSON_SERVICE_XSD, PROP_ENDPOINT_CONSULTRN_V2_CBSS_PERSON_SERVICE, CONSULTRN_V2_CBSS_PERSON_SERVICE_UDDI);
   }

   private static GenericRequest getConsultrnService(SAMLToken token, String soapAction, String xsd, String endpointProp, String uddi) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAML token is null.");
      Validate.notEmpty(soapAction, "Required parameter SOAP action is null or empty");
      return (new GenericRequest()).setEndpoint(config.getProperty(endpointProp, uddi)).setCredential(token, TokenType.SAML).setSoapAction(soapAction).addHandlerChain(HandlerChainUtil.buildChainWithValidator(PROP_VALIDATION_INCOMING_CONSULTRN, xsd));
   }
}
