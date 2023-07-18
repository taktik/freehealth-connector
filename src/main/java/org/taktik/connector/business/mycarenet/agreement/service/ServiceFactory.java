package org.taktik.connector.business.mycarenet.agreement.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_AGREEMENT = "endpoint.agreement";
   private static final List<String> expectedProps = new ArrayList();
   private static final Configuration config;

   private ServiceFactory() {
   }

   public static GenericRequest getAgreementPort(SAMLToken token) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.agreement", "$uddi{uddi:ehealth-fgov-be:business:mycareneteagreement:v1}")).setCredential(token, TokenType.SAML).addDefaulHandlerChain();
   }

   static {
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
