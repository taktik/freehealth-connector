package be.ehealth.technicalconnector.config;

import be.ehealth.technicalconnector.config.impl.ConfigValidatorImpl;
import be.ehealth.technicalconnector.config.impl.ConfigurationImpl;
import be.ehealth.technicalconnector.config.util.ConfigUtil;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.shutdown.DeleteFileOnExitShutdownHook;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
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
   public static final String SYSTEM_PROP_CONFIG_LOCATION = "be.ehealth.technicalconnector.config.location";
   public static final String DEFAULT_CONFIG = "/be.ehealth.technicalconnector.properties";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigFactory.class);
   private static final Map<Set<String>, ConfigValidator> CACHE = new HashMap();
   private static String configLocation = "/be.ehealth.technicalconnector.properties";
   private static Configuration configuration;

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
         if (LOG.isDebugEnabled()) {
            LOG.debug("Adding ConfigValidator to cache with expectedProps {}", StringUtils.join(cacheKey, ","));
         }

         CACHE.put(cacheKey, new ConfigValidatorImpl(expectedProps, configuration));
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

   public static String getConfigLocation() {
      return System.getProperties().containsKey("be.ehealth.technicalconnector.config.location") ? System.getProperty("be.ehealth.technicalconnector.config.location") : configLocation;
   }

   public static void setConfigLocation(String configLocation) throws TechnicalConnectorException {
      String locationToSet = configLocation;
      if (configLocation == null) {
         if (ConfigUtil.isNet()) {
            locationToSet = StringUtils.replace("/be.ehealth.technicalconnector.properties", "/", ".\\");
         } else {
            locationToSet = "/be.ehealth.technicalconnector.properties";
         }
      }

      ConnectorIOUtils.getResourceFilePath(locationToSet);
      ConfigFactory.configLocation = locationToSet;
      ConfigurationImpl.reset();
      invalidate();
   }

   public static void setLocation(InputStream is) throws TechnicalConnectorException {
      Validate.notNull(is);
      FileOutputStream fos = null;

      try {
         File file = File.createTempFile("inputStream", ".properties");
         DeleteFileOnExitShutdownHook.deleteOnExit(file);
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

   public static void setConfiguration(Configuration configuration) {
      ConfigFactory.configuration = configuration;
   }
}
