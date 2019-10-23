package org.taktik.connector.business.mycarenet.attestv2.service;

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
   private static final String PROP_ENDPOINT_ATTEST_V2 = "endpoint.eattestv2";
   public static final String PROP_MESSAGE_PROTOCOLE_SCHEMA_VERSION_V2 = "mycarenet.attest.v2.message.protocole.schema.version";
   private static final List<String> expectedProps = new ArrayList();
   private static final Configuration config;

   private ServiceFactory() {
   }

   public static GenericRequest getAttestPort(SAMLToken token) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      String endPoint = config.getProperty("endpoint.eattestv2", "$uddi{uddi:ehealth-fgov-be:business:mycareneteattest:v2}");
      //String endPoint = config.getProperty("endpoint.eattestv2", "https://services-acpt.ehealth.fgov.be/MyCareNet/eAttest/v2");
      return (new GenericRequest()).setEndpoint(endPoint).setCredential(token, TokenType.SAML).addDefaulHandlerChain();
   }

   static {
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
