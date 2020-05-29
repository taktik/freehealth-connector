package org.taktik.connector.business.mycarenet.mhm.service;

import org.apache.commons.lang.Validate;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;

import java.util.ArrayList;

public final class ServiceFactory {
   private static final Configuration config = ConfigFactory.getConfigValidator(new ArrayList<>());

   private ServiceFactory() {
   }

   public static GenericRequest getSubscriptionPort(SAMLToken token) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      String endPoint = config.getProperty("endpoint.mcn.medicalhousemembership", "$uddi{uddi:ehealth-fgov-be:business:mycarenetmhm:v1}");
      return (new GenericRequest()).setEndpoint(endPoint).setCredential(token, TokenType.SAML).addDefaulHandlerChain();
   }
}
