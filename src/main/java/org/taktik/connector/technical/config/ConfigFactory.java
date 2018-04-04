package org.taktik.connector.technical.config;

import org.taktik.connector.technical.config.impl.ConfigValidatorImpl;
import org.taktik.connector.technical.config.impl.ConfigurationImpl;
import org.taktik.connector.technical.config.util.ConfigUtil;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigFactory {
   public static final String SYSTEM_PROP_CONFIG_LOCATION = "org.taktik.connector.technical.config.location";
   public static final String DEFAULT_CONFIG = "/acpt/org.taktik.connector.technical.properties";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigFactory.class);
   private static final Map<Set<String>, ConfigValidator> CACHE = new HashMap();
   private static String configLocation = "/acpt/org.taktik.connector.technical.properties";

   private ConfigFactory() {
      throw new UnsupportedOperationException();
   }

   public static ConfigValidator getConfigValidator() {
      return getConfigValidator((List)null);
   }

   public static ConfigValidator getConfigValidator(List<String> expectedProps) {
      Set<String> cacheKey = new TreeSet();
      if (expectedProps != null) {
         cacheKey.addAll(expectedProps);
      }

      if (!CACHE.containsKey(cacheKey)) {
         LOG.debug("Adding ConfigValidator to cache with expectedProps " + StringUtils.join(cacheKey, ","));
         CACHE.put(cacheKey, new ConfigValidatorImpl(expectedProps));
      }

      return (ConfigValidator)CACHE.get(cacheKey);
   }

   public static ConfigValidator getConfigValidatorFor(String... expectedProps) {
      if (expectedProps == null) {
         throw new IllegalArgumentException("getConfigValidatorFor cannot have a null parameter");
      } else {
         return getConfigValidator(Arrays.asList(expectedProps));
      }
   }

   public static void invalidate() {
      Iterator i$ = CACHE.values().iterator();

      while(i$.hasNext()) {
         ConfigValidator validator = (ConfigValidator)i$.next();
         validator.invalidateCache();
      }

   }

   public static String getConfigLocation() throws TechnicalConnectorException {
      return System.getProperties().containsKey("org.taktik.connector.technical.config.location") ? System.getProperty("org.taktik.connector.technical.config.location") : configLocation;
   }

   public static void setConfigLocation(String configLocation) throws TechnicalConnectorException {
      String locationToSet = configLocation;
      if (configLocation == null) {
         if (ConfigUtil.isNet()) {
            locationToSet = StringUtils.replace("/acpt/org.taktik.connector.technical.properties", "/", ".\\");
         } else {
            locationToSet = "/acpt/org.taktik.connector.technical.properties";
         }
      }

      ConnectorIOUtils.getResourceFilePath(locationToSet);
      configLocation = locationToSet;
      ConfigurationImpl.reset();
      invalidate();
   }

   public static void setLocation(InputStream is) throws TechnicalConnectorException {
      Validate.notNull(is);
      FileOutputStream fos = null;

      try {
         File file = File.createTempFile("inputStream", ".properties");
         file.deleteOnExit();
         fos = new FileOutputStream(file);
         Properties prop = new Properties();
         prop.load(is);
         prop.store(fos, "Config loaded from inputstream");
         setConfigLocation(file.getAbsolutePath());
      } catch (IOException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, new Object[]{"Problem with the temporary file inputstream.properties", var7});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)fos);
      }

   }
}
