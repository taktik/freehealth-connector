package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationImpl extends AbstractConfigurationImpl {
   public static final String SYSPROP_MODULE_LOADING = "be.ehealth.technicalconnector.config.modules.load";
   public static final String SYSPROP_CONFIG_LOADING = "be.ehealth.technicalconnector.config.load";
   private static volatile ConfigurationImpl instance;
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationImpl.class);
   private boolean reloadAction;
   private Properties properties;

   private ConfigurationImpl() {
   }

   public static synchronized void reset() {
      instance = null;
   }

   public void reload() throws TechnicalConnectorException {
      if (instance == null) {
         throw new IllegalStateException("reload called while instance is not initialized");
      } else {
         this.reloadAction = true;
         ConfigurationModuleLoader.load(instance);
         this.reloadAction = false;
      }
   }

   public boolean isReloading() {
      return this.reloadAction;
   }

   private void init() {
      if (this.properties == null) {
         try {
            this.properties = new RecursiveProperties();
            this.load(ConfigFactory.getConfigLocation(), this.properties);
         } catch (TechnicalConnectorException var2) {
            LOG.error("Loading properties failed", var2);
         }
      }

   }

   private boolean loadLocation(String location, Properties props) {
      LOG.info("Trying to load properties: {}", location);
      InputStream is = null;

      boolean var5;
      try {
         is = ConnectorIOUtils.getResourceAsStream(location);
         if (is != null) {
            props.load(is);
         }

         return true;
      } catch (Exception var9) {
         var5 = false;
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

      return var5;
   }

   private void load(String location, Properties props) {
      if (!"false".equals(System.getProperty("be.ehealth.technicalconnector.config.load", "true"))) {
         boolean loaded = this.loadLocation(location, props);
         if (!loaded && "/be.ehealth.technicalconnector.properties".equals(location)) {
            String moddedLocation = "./" + location;
            loaded = this.loadLocation(moddedLocation, props);
         }

         if (!loaded) {
            LOG.error("Loading properties failed: {}", location);
         }

      }
   }

   /** @deprecated */
   @Deprecated
   public void setConfigLocation(String location) throws TechnicalConnectorException {
      ConfigFactory.setConfigLocation(location);
   }

   public Configuration getCurrentConfig() throws TechnicalConnectorException {
      return getInstance();
   }

   public static synchronized Configuration getInstance() throws TechnicalConnectorException {
      if (instance == null) {
         instance = new ConfigurationImpl();
         if ("true".equals(System.getProperty("be.ehealth.technicalconnector.config.modules.load", "true"))) {
            instance.reload();
         }
      }

      return instance;
   }

   public void setProperty(String key, String value) {
      if (!StringUtils.isEmpty(key)) {
         this.props().remove(key);
         if (value != null) {
            this.props().put(key, value);
         }
      }

   }

   public boolean hasProperty(String key) {
      return this.props().containsKey(key);
   }

   public String getProperty(String key, String defaultValue) {
      String value = this.props().getProperty(key, defaultValue);
      this.analysePropertiesFile(key, defaultValue, value);
      return key == null || !this.endpointToTrim(key) && !this.elseToTrim(key) ? value : StringUtils.trim(value);
   }

   private void analysePropertiesFile(String key, String defaultValue, String value) {
      if (LOG.isDebugEnabled() && defaultValue != null && defaultValue.equals(value) && this.props().containsKey(key)) {
         LOG.debug("CONFIG CLEANUP [{}] configured value is same as default value.", key);
      }

   }

   public boolean endpointToTrim(String key) {
      return !key.toLowerCase().contains("password") && "true".equals(this.props().getProperty("configuration.properties.trim", "false").trim());
   }

   public boolean elseToTrim(String key) {
      return key.toLowerCase().startsWith("endpoint") && "true".equals(this.props().getProperty("remove.trail.withspaces", "true").trim());
   }

   /** @deprecated */
   @Deprecated
   public Properties getProperties() {
      return this.props();
   }

   private Properties props() {
      this.init();
      return this.properties;
   }

   public void invalidate() {
      ConfigurationModuleLoader.unload();
   }
}
