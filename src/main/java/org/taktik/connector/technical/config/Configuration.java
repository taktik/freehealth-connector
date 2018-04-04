package org.taktik.connector.technical.config;

import org.taktik.connector.technical.config.domain.Duration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface Configuration {
   String getProperty(String key, String defaultValue);

   Long getLongProperty(String key, Long defaultValue);

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

   /** @deprecated */
   @Deprecated
   void setConfigLocation(String var1) throws TechnicalConnectorException;

   Configuration getCurrentConfig() throws TechnicalConnectorException;

   void invalidate() throws TechnicalConnectorException;

   void reload() throws TechnicalConnectorException;

   boolean isReloading();
}
