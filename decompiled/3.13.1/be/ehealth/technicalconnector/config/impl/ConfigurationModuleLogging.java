package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.lang.reflect.Method;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationModuleLogging implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleLogging.class);
   private static final String PROP_LOG_ENABLED = "connector.logger.enabled";
   private static final boolean PROP_LOG_ENABLED_DEFAULT = true;
   private static final String PROP_LOG_FRAMEWORK_CLASS = "connector.logger.framework";
   private static final String PROP_LOG_FRAMEWORK_DEFAULT = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleLoggingLog4j";
   private Object logger;

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      if (!config.getBooleanProperty("connector.logger.enabled", true).booleanValue()) {
         LOG.debug("ConfigurationModuleLogging is disabled.");
      } else if (config instanceof ConfigurationImpl) {
         ConfigurationImpl conf = (ConfigurationImpl)config;
         String className = config.getProperty("connector.logger.framework", "be.ehealth.technicalconnector.config.impl.ConfigurationModuleLoggingLog4j");

         try {
            Class loggerClass = Class.forName(className);
            this.logger = loggerClass.newInstance();
            Method method = loggerClass.getDeclaredMethod("init", Properties.class);
            method.invoke(this.logger, conf.getProperties());
         } catch (Exception var6) {
            LOG.error(var6.getClass().getSimpleName() + ": " + var6.getMessage());
         }
      } else {
         LOG.warn("Unsupported config instance: " + config.getClass());
      }

   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());

      try {
         Method method = this.logger.getClass().getDeclaredMethod("unload");
         method.invoke(this.logger);
      } catch (Exception var2) {
         LOG.error(var2.getClass().getSimpleName() + ": " + var2.getMessage());
      }

   }
}
