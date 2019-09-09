package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.domain.Duration;
import org.taktik.connector.technical.exception.ConfigurationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigValidatorImpl implements ConfigValidator {
   public static final String PROP_VALIDATOR_ACTIVATOR = "org.taktik.connector.technical.config.impl.ConfigValidatorImpl.throwErrorOnInvalidconfig";
   private static final Boolean PROP_VALIDATOR_ACTIVATOR_DEFAULT;
   private static final Logger LOG;
   private Boolean valid;
   private Object[] unfoundProps = new Object[0];
   private List<String> expectedProps = new ArrayList();
   private Configuration config;

   public ConfigValidatorImpl(List<String> expectedProps) {
      if (expectedProps != null) {
         this.expectedProps.addAll(expectedProps);
      }

   }

   private void init() throws TechnicalConnectorException {
      if (this.config == null) {
         this.config = ConfigurationImpl.getInstance();
         if (this.valid == null) {
            this.valid = this.validate();
         }
      }

   }

   public final Configuration getConfig() throws TechnicalConnectorException {
      this.init();
      if (this.valid) {
         return this.config;
      } else {
         StringBuilder sb = new StringBuilder("Could not find properties. ");
         Object[] arr$ = this.unfoundProps;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Object propObj = arr$[i$];
            sb.append("[" + propObj.toString() + "]");
         }

         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_CONFIG, new Object[]{sb.toString()});
      }
   }

   private boolean validate() {
      this.unfoundProps = new Object[0];
      Iterator i$ = this.expectedProps.iterator();

      while(i$.hasNext()) {
         String prop = (String)i$.next();
         boolean validProp = this.config.containsKey(prop);
         boolean validMatchProp = this.config.containsKey(prop + ".1");
         if (validProp || validMatchProp) {
            validProp = true;
         }

         if (!validProp) {
            LOG.warn("Could not find property:" + prop);
            this.unfoundProps = ArrayUtils.add(this.unfoundProps, prop);
         }
      }

      return ArrayUtils.isEmpty(this.unfoundProps);
   }

   public List<Object> getUnfoundPropertiesAfterValidation() throws TechnicalConnectorException {
      this.init();
      return Arrays.asList(this.unfoundProps);
   }

   public final boolean isValid() throws TechnicalConnectorException {
      this.init();
      return this.valid;
   }

   public final void invalidateCache() {
      this.config = null;
      this.valid = null;
      this.unfoundProps = new Object[0];
   }

   public final String getProperty(String key, String defaultValue) {
      try {
         return this.getConfig().getProperty(key, defaultValue);
      } catch (TechnicalConnectorException var4) {
         return (String)this.processException(var4);
      }
   }

   private Object processException(Exception e) {
      if (this.config.getBooleanProperty("org.taktik.connector.technical.config.impl.ConfigValidatorImpl.throwErrorOnInvalidconfig", PROP_VALIDATOR_ACTIVATOR_DEFAULT)) {
         LOG.error("No Valid config", e);
         throw new ConfigurationException("No Valid config. Reason[" + e.getMessage() + "]");
      } else {
         LOG.warn("No Valid config. Reason[" + e.getMessage() + "]");
         return null;
      }
   }

   public final Long getLongProperty(String key, Long defaultValue) {
      try {
         return this.getConfig().getLongProperty(key, defaultValue);
      } catch (TechnicalConnectorException var4) {
         return (Long)this.processException(var4);
      }
   }

   public final Integer getIntegerProperty(String key, Integer defaultValue) {
      try {
         return this.getConfig().getIntegerProperty(key, defaultValue);
      } catch (TechnicalConnectorException var4) {
         return (Integer)this.processException(var4);
      }
   }

   public final Boolean getBooleanProperty(String key, Boolean defaultValue) {
      try {
         return this.getConfig().getBooleanProperty(key, defaultValue);
      } catch (TechnicalConnectorException var4) {
         return (Boolean)this.processException(var4);
      }
   }

   public Duration getDurationProperty(String key, Long defaultValue, TimeUnit defaultTimeUnit) {
      try {
         return this.getConfig().getDurationProperty(key, defaultValue, defaultTimeUnit);
      } catch (TechnicalConnectorException var5) {
         return (Duration)this.processException(var5);
      }
   }

   public boolean hasDurationProperty(String key) {
      try {
         return this.getConfig().hasDurationProperty(key);
      } catch (TechnicalConnectorException var3) {
         return (Boolean) this.processException(var3);
      }
   }

   public final String getProperty(String key) {
      try {
         return this.getConfig().getProperty(key);
      } catch (TechnicalConnectorException var3) {
         return (String)this.processException(var3);
      }
   }

   public final boolean containsKey(String key) {
      try {
         return this.getConfig().containsKey(key);
      } catch (TechnicalConnectorException var3) {
         this.processException(var3);
         return false;
      }
   }

   public final URL getURLProperty(String property) {
      try {
         return this.getConfig().getURLProperty(property);
      } catch (TechnicalConnectorException var3) {
         return (URL)this.processException(var3);
      }
   }

   public final void setProperty(String key, String value) {
      try {
         this.getConfig().setProperty(key, value);
      } catch (TechnicalConnectorException var4) {
         LOG.warn("No valid config. setProperty(" + key + ", value) didn't work!!!", var4);
      }

   }

   public final boolean hasProperty(String key) {
      try {
         return this.getConfig().hasProperty(key);
      } catch (TechnicalConnectorException var3) {
         this.processException(var3);
         return false;
      }
   }

   public boolean hasMatchingProperty(String rootKey) {
      try {
         return this.getConfig().hasMatchingProperty(rootKey);
      } catch (TechnicalConnectorException var3) {
         this.processException(var3);
         return false;
      }
   }

   public final List<String> getMatchingProperties(String rootKey) {
      try {
         return this.getConfig().getMatchingProperties(rootKey);
      } catch (TechnicalConnectorException var3) {
         this.processException(var3);
         return new ArrayList();
      }
   }

   public final void setConfigLocation(String location) throws TechnicalConnectorException {
      ConfigFactory.setConfigLocation(location);
      this.init();
      this.config.setConfigLocation(location);
   }

   public final Configuration getCurrentConfig() {
      try {
         return this.getConfig().getCurrentConfig();
      } catch (TechnicalConnectorException var2) {
         return (Configuration)this.processException(var2);
      }
   }

   public void invalidate() throws TechnicalConnectorException {
      this.getConfig().invalidate();
   }

   public void reload() throws TechnicalConnectorException {
      this.getConfig().reload();
   }

   public boolean isReloading() {
      try {
         return this.getConfig().isReloading();
      } catch (TechnicalConnectorException var2) {
         return (Boolean) this.processException(var2);
      }
   }

   static {
      PROP_VALIDATOR_ACTIVATOR_DEFAULT = Boolean.TRUE;
      LOG = LoggerFactory.getLogger(ConfigValidatorImpl.class);
   }
}
