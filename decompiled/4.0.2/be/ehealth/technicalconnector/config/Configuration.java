package be.ehealth.technicalconnector.config;

import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface Configuration {
   String getProperty(String var1, String var2);

   Long getLongProperty(String var1, Long var2);

   Integer getIntegerProperty(String var1, Integer var2);

   Boolean getBooleanProperty(String var1, Boolean var2);

   String getProperty(String var1);

   boolean containsKey(String var1);

   URL getURLProperty(String var1);

   void setProperty(String var1, String var2);

   boolean hasProperty(String var1);

   boolean hasMatchingProperty(String var1);

   Duration getDurationProperty(String var1, Long var2, TimeUnit var3);

   boolean hasDurationProperty(String var1);

   List<String> getMatchingProperties(String var1);

   Configuration getCurrentConfig() throws TechnicalConnectorException;

   void invalidate() throws TechnicalConnectorException;

   void reload() throws TechnicalConnectorException;

   boolean isReloading();
}
