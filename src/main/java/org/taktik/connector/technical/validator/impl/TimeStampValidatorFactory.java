package org.taktik.connector.technical.validator.impl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import org.taktik.connector.technical.utils.KeyStoreManager;
import org.taktik.connector.technical.validator.TimeStampValidator;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

public final class TimeStampValidatorFactory {
   private static final String TIMESTAMP_SIGNATURE_KEYSTORE_PWD = "timestamp.signature.keystore.pwd";
   private static final String TIMESTAMP_SIGNATURE_KEYSTORE_PATH = "timestamp.signature.keystore.path";
   private static final String PROP_KEYSTORE_DIR = "KEYSTORE_DIR";
   private static final String PROP_TIMESTAMPVALIDATOR_FACTORY = "timestamp.validator.factory";
   private static final String PROP_DEFAULT_TIMESTAMPVALIDATOR_FACTORY = "org.taktik.connector.technical.validator.impl.TimeStampValidatorImpl";
   private static TimeStampValidator validatorInstance;
   private static ConfigurableFactoryHelper<TimeStampValidator> helperFactory = new ConfigurableFactoryHelper("timestamp.validator.factory", "org.taktik.connector.technical.validator.impl.TimeStampValidatorImpl");

   private TimeStampValidatorFactory() {
      throw new UnsupportedOperationException();
   }

   public static TimeStampValidator getInstance() throws TechnicalConnectorException {
      if (validatorInstance == null) {
         validatorInstance = (TimeStampValidator)helperFactory.getImplementation(init(), false);
      }

      return validatorInstance;
   }

   private static Map<String, Object> init() throws TechnicalConnectorException {
      Configuration config = ConfigFactory.getConfigValidatorFor("timestamp.signature.keystore.path", "timestamp.signature.keystore.pwd", "KEYSTORE_DIR");
      Map<String, Object> parameterMap = new HashMap();
      String keystorePath = config.getProperty("KEYSTORE_DIR") + config.getProperty("timestamp.signature.keystore.path");
      KeyStoreManager keyStoreManager = new KeyStoreManager(keystorePath, config.getProperty("timestamp.signature.keystore.pwd").toCharArray());
      KeyStore keyStore = keyStoreManager.getKeyStore();
      parameterMap.put("timestampvalidatior.keystore", keyStore);
      return parameterMap;
   }

   /** @deprecated */
   @Deprecated
   public static TimeStampValidator getTimeStampValidator() throws TechnicalConnectorException {
      return getInstance();
   }

   public static void reset() {
      validatorInstance = null;
   }
}
