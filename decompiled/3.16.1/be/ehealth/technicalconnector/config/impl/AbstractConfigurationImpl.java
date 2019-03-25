package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.domain.Duration;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConfigurationImpl implements Configuration {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractConfigurationImpl.class);
   private static final String DURATION = ".duration";
   private static final String TIMEUNIT = ".timeunit";

   public Long getLongProperty(String key, Long defaultValue) {
      return Long.valueOf(this.getProperty(key, "" + defaultValue));
   }

   public Integer getIntegerProperty(String key, Integer defaultValue) {
      return Integer.valueOf(this.getProperty(key, "" + defaultValue));
   }

   public Boolean getBooleanProperty(String key, Boolean defaultValue) {
      return Boolean.valueOf(this.getProperty(key, "" + defaultValue));
   }

   public Duration getDurationProperty(String key, Long defaultValue, TimeUnit defaultTimeUnit) {
      long duration = this.getLongProperty(key + ".duration", defaultValue);
      String timeUnit = this.getProperty(key + ".timeunit", defaultTimeUnit.name());
      return new Duration(duration, TimeUnit.valueOf(timeUnit));
   }

   public boolean hasDurationProperty(String key) {
      return this.hasProperty(key + ".duration") && this.hasProperty(key + ".timeunit");
   }

   public URL getURLProperty(String key) {
      String wsdl = this.getProperty(key);

      try {
         return new URL(wsdl);
      } catch (MalformedURLException var4) {
         LOG.error("No valid url: " + wsdl);
         return null;
      }
   }

   public String getProperty(String key) {
      return this.getProperty(key, (String)null);
   }

   public boolean containsKey(String key) {
      return this.hasProperty(key);
   }

   public abstract void setProperty(String var1, String var2);

   public abstract boolean hasProperty(String var1);

   public abstract String getProperty(String var1, String var2);

   public boolean hasMatchingProperty(String rootKey) {
      return this.hasMatchingProperty(rootKey, 1);
   }

   public boolean hasMatchingProperty(String rootKey, int i) {
      return this.hasProperty(this.generateMatchtingPropertyKey(rootKey, i));
   }

   private String getMatchtingProperty(String rootKey, int i) {
      return this.getProperty(this.generateMatchtingPropertyKey(rootKey, i));
   }

   public List<String> getMatchingProperties(String rootKey) {
      int i = 1;

      ArrayList ret;
      for(ret = new ArrayList(); this.hasMatchingProperty(rootKey, i); ++i) {
         ret.add(this.getMatchtingProperty(rootKey, i));
      }

      return ret;
   }

   private String generateMatchtingPropertyKey(String rootKey, int i) {
      return rootKey + "." + i;
   }
}
