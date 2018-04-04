package org.taktik.connector.business.mycarenet.attest.service;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_ATTEST = "endpoint.attest";
   private static final List<String> expectedProps = new ArrayList();
   private static final Configuration config;

   public static GenericRequest getAttestPort(SAMLToken token) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.attest", "$uddi{uddi:ehealth-fgov-be:business:mycareneteattest:v1}")).setCredential(token, TokenType.SAML).addDefaulHandlerChain();
   }

   static {
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
