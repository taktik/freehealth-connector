package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.exception.ConfigurationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigValidatorImpl implements ConfigValidator {
   public static final String PROP_VALIDATOR_ACTIVATOR = "be.ehealth.technicalconnector.config.impl.ConfigValidatorImpl.throwErrorOnInvalidconfig";
   private static final Boolean PROP_VALIDATOR_ACTIVATOR_DEFAULT;
   private static final Logger LOG;
   private Boolean valid;
   private Object[] unfoundProps;
   private List<String> expectedProps;
   private Configuration config;

   public ConfigValidatorImpl(List<String> expectedProps) {
      this(expectedProps, (Configuration)null);
   }

   public ConfigValidatorImpl(List<String> expectedProps, Configuration config) {
      this.unfoundProps = new Object[0];
      this.expectedProps = new ArrayList();
      if (expectedProps != null) {
         this.expectedProps.addAll(expectedProps);
      }

      this.config = config;
   }

   private void init() throws TechnicalConnectorException {
      if (this.config == null) {
         this.config = ConfigurationImpl.getInstance();
      }

      if (this.valid == null) {
         this.valid = this.validate();
      }

   }

   public final Configuration getConfig() throws TechnicalConnectorException {
      this.init();
      if (Boolean.TRUE.equals(this.valid)) {
         return this.config;
      } else {
         StringBuilder sb = new StringBuilder("Could not find properties. ");
         Object[] var2 = this.unfoundProps;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Object propObj = var2[var4];
            sb.append("[" + propObj.toString() + "]");
         }

         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_CONFIG, new Object[]{sb.toString()});
      }
   }

   private boolean validate() {
      this.unfoundProps = new Object[0];
      Iterator var1 = this.expectedProps.iterator();

      while(var1.hasNext()) {
         String prop = (String)var1.next();
         boolean validProp = this.config.containsKey(prop);
         boolean validMatchProp = this.config.containsKey(prop + ".1");
         if (validProp || validMatchProp) {
            validProp = true;
         }

         if (!validProp) {
            LOG.warn("Could not find property: {}", prop);
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
      if (Boolean.TRUE.equals(this.config.getBooleanProperty("be.ehealth.technicalconnector.config.impl.ConfigValidatorImpl.throwErrorOnInvalidconfig", PROP_VALIDATOR_ACTIVATOR_DEFAULT))) {
         LOG.error("No Valid config", e);
         throw new ConfigurationException("No Valid config. Reason[" + e.getMessage() + "]");
      } else {
         LOG.warn("No Valid config. Reason[{}]", e.getMessage());
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
         return (Boolean)this.processException(var3);
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
         return (Boolean)this.processException(var2);
      }
   }

   static {
      PROP_VALIDATOR_ACTIVATOR_DEFAULT = Boolean.TRUE;
      LOG = LoggerFactory.getLogger(ConfigValidatorImpl.class);
   }
}
