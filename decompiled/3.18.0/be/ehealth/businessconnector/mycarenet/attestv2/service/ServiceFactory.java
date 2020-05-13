package be.ehealth.businessconnector.mycarenet.attestv2.service;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
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
      return (new GenericRequest()).setEndpoint(config.getProperty("endpoint.eattestv2", "$uddi{uddi:ehealth-fgov-be:business:mycareneteattest:v2}")).setCredential(token, TokenType.SAML).addDefaulHandlerChain();
   }

   static {
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}
