package org.taktik.connector.technical.service.sts.security;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.impl.beid.BeIDProviderAdaptor;
import java.security.AuthProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ProviderFactory {
   private static final Logger LOG = LoggerFactory.getLogger(ProviderFactory.class);
   private static final String PROP_PROVIDER = "provider.class";
   private static final String DEFAULT_PROVIDER = BeIDProviderAdaptor.class.getName();
   private static Configuration config = ConfigFactory.getConfigValidator();

   public static AuthProvider getProvider() throws TechnicalConnectorException {
      String providerClassName = config.getProperty("provider.class", DEFAULT_PROVIDER);

      try {
         Class<?> provider = Class.forName(providerClassName);
         Object providerObject = provider.newInstance();
         if (providerObject instanceof ProviderAdaptor) {
            return (AuthProvider)((ProviderAdaptor)providerObject).getProvider();
         } else {
            String msg = "Class with name [" + provider + "] is not an instance of RevocationStatusChecker, but an instance of [" + providerObject.getClass() + "]";
            LOG.debug(msg);
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROVIDER_INSTANCIATION, new Object[]{msg});
         }
      } catch (Exception var4) {
         LOG.error(var4.getClass().getSimpleName() + ": " + var4.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROVIDER_INSTANCIATION, var4, new Object[]{providerClassName, var4.getMessage()});
      }
   }
}
