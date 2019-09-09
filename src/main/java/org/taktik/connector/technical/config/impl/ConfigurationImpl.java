package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationImpl extends AbstractConfigurationImpl {
   public static final String SYSPROP_MODULE_LOADING = "org.taktik.connector.technical.config.modules.load";
   public static final String SYSPROP_CONFIG_LOADING = "org.taktik.connector.technical.config.load";
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

   private void load(String file, Properties props) {
      if (!"false".equals(System.getProperty("org.taktik.connector.technical.config.load", "true"))) {
      InputStream is = null;

      try {
         LOG.info("Trying to load properties: " + file);

         try {
            is = ConnectorIOUtils.getResourceAsStream(file);
         } catch (TechnicalConnectorException var11) {
            if ("/acpt/org.taktik.connector.technical.properties".equals(file)) {
               String moddedFile = "./" + file;
               LOG.info("Trying to load properties: " + file);
               is = ConnectorIOUtils.getResourceAsStream(moddedFile);
            }
         }

         if (is != null) {
            props.load(is);
         } else {
            LOG.error("Loading properties failed: " + file);
         }
      } catch (TechnicalConnectorException var12) {
         LOG.error("Loading properties failed: " + file, var12);
      } catch (IOException var13) {
         LOG.error("Loading properties failed: " + file, var13);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
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
         if ("true".equals(System.getProperty("org.taktik.connector.technical.config.modules.load", "true"))) {
            instance.reload();
         }
      }

      return instance;
   }

   public void setProperty(String key, String value) {
      if (!StringUtils.isEmpty(key)) {
         this.getProperties().remove(key);
         if (value != null) {
            this.getProperties().put(key, value);
         }
      }

   }

   public boolean hasProperty(String key) {
      return this.getProperties().containsKey(key);
   }

   public String getProperty(String key, String defaultValue) {
      String value = this.getProperties().getProperty(key, defaultValue);
      return key == null || !this.endpointToTrim(key) && !this.elseToTrim(key) ? value : StringUtils.trim(value);
   }

   public boolean endpointToTrim(String key) {
      return !key.toLowerCase().contains("password") && "true".equals(this.getProperties().getProperty("configuration.properties.trim", "false").trim());
   }

   public boolean elseToTrim(String key) {
      return key.toLowerCase().startsWith("endpoint") && "true".equals(this.getProperties().getProperty("remove.trail.withspaces", "true").trim());
   }

   /** @deprecated */
   @Deprecated
   public Properties getProperties() {
      this.init();
      return this.properties;
   }

   public void invalidate() {
      ConfigurationModuleLoader.unload();
   }
}
