package be.ehealth.technicalconnector.service.sts.security;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.impl.beid.BeIDKeyStoreAdaptor;
import java.security.KeyStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KeyStoreFactory {
   private static final String DEFAULT_KEYSTOREADAPTOR = BeIDKeyStoreAdaptor.class.getName();
   public static final String PROP_KEYSTOREADAPTOR_CLASS = "keystoreadaptor.class";
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static final Logger LOG = LoggerFactory.getLogger(KeyStoreFactory.class);

   private KeyStoreFactory() {
   }

   public static KeyStore getKeyStore() throws TechnicalConnectorException {
      String keyStoreClassName = config.getProperty("keystoreadaptor.class", DEFAULT_KEYSTOREADAPTOR);
      LOG.debug("Keystore class name : {}", keyStoreClassName);

      try {
         Class<?> callback = Class.forName(keyStoreClassName);
         Object adaptor = callback.newInstance();
         if (adaptor instanceof KeyStoreAdaptor) {
            return ((KeyStoreAdaptor)adaptor).getKeyStore();
         } else {
            String msg = "Class with name [" + keyStoreClassName + "] is not an instance of RevocationStatusChecker, but an instance of [" + adaptor.getClass() + "]";
            LOG.warn(msg);
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.PROVIDER_INSTANCIATION, new Object[]{msg});
         }
      } catch (Exception var4) {
         LOG.error("InstantiationException", var4);
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_EIDPINLCALLBACKHANDLERFACTORY, var4, new Object[]{keyStoreClassName});
      }
   }
}
