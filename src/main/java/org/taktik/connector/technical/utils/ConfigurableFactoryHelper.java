package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.SilentInstantiationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurableFactoryHelper<T> {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurableFactoryHelper.class);
   private final Map<ConfigurableFactoryHelper.CacheKey, T> cache;
   private final String classPropertyName;
   private Configuration config;
   private final String defaultClassPropertyName;

   public ConfigurableFactoryHelper(String classPropertyName, String defaultClassPropertyName) {
      this.cache = new HashMap<>();
      this.classPropertyName = classPropertyName;
      this.defaultClassPropertyName = defaultClassPropertyName;
      this.config = ConfigFactory.getConfigValidator();
   }

   /** @deprecated */
   @Deprecated
   public ConfigurableFactoryHelper(String classPropertyName, String defaultClassPropertyName, Class<T> clazz) {
      this(classPropertyName, defaultClassPropertyName);
   }

   private T createAndConfigureImplementation(String headerClassName, Map<String, Object> configParameters, boolean silent) throws TechnicalConnectorException {
      Object providerObject = null;

      try {
         Class provider = Class.forName(headerClassName);

         try {
            providerObject = provider.newInstance();
         } catch (IllegalAccessException var9) {
            LOG.debug("Default constructor is not public. Trying to invoke getInstance().");
            Method method = provider.getMethod("getInstance");
            providerObject = method.invoke(provider);
         }

         if (providerObject != null) {
            this.init((T) providerObject, configParameters, silent);
         }

         return (T) providerObject;
      } catch (Exception var10) {
         if (!silent) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HEADER_INSTANCIATION, var10, new Object[]{headerClassName});
         } else {
            return null;
         }
      }
   }

   public T getImplementation() throws TechnicalConnectorException {
      return (T) this.getImplementation(new HashMap(), true, false);
   }

   public T getImplementation(boolean useCache) throws TechnicalConnectorException {
      return (T) this.getImplementation(new HashMap(), useCache, false);
   }

   public T getImplementation(Map<String, Object> configParameters) throws TechnicalConnectorException {
      return this.getImplementation(configParameters, true, false);
   }

   public T getImplementation(Map<String, Object> hashMap, boolean usecache) throws TechnicalConnectorException {
      return this.getImplementation(hashMap, usecache, false);
   }

   public T getImplementation(Map<String, Object> configParameters, boolean useCaching, boolean silent) throws TechnicalConnectorException {
      String headerClassName = this.config.getProperty(this.classPropertyName, this.defaultClassPropertyName);
      ConfigurableFactoryHelper.CacheKey cacheKey = new ConfigurableFactoryHelper.CacheKey(configParameters, headerClassName);
      if (useCaching && this.cache.containsKey(cacheKey)) {
         return this.cache.get(cacheKey);
      } else if (headerClassName == null && !silent) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"No valid configuration " + this.classPropertyName + " not found."});
      } else {
         T result = this.getImplementation(headerClassName, configParameters, useCaching, silent);
         if (result == null && !silent) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"No valid configuration " + this.classPropertyName + " not found."});
         } else {
            return result;
         }
      }
   }

   private T getImplementation(String headerClassName, Map<String, Object> configParameters, boolean useCache, boolean silent) throws TechnicalConnectorException {
      ConfigurableFactoryHelper.CacheKey key = new ConfigurableFactoryHelper.CacheKey(configParameters, headerClassName);
      if (useCache && this.cache.containsKey(key)) {
         return this.cache.get(key);
      } else if (headerClassName != null && !headerClassName.isEmpty()) {
         T result = this.createAndConfigureImplementation(headerClassName, configParameters, silent);
         if (useCache) {
            this.cache.put(key, result);
         }

         return result;
      } else {
         return null;
      }
   }

   public List<T> getImplementations() throws TechnicalConnectorException {
      return this.getImplementations(true);
   }

   public List<T> getImplementations(boolean useCache) throws TechnicalConnectorException {
      return this.getImplementations(useCache, true);
   }

   public List<T> getImplementations(boolean useCache, boolean silent) throws TechnicalConnectorException {
      return this.getImplementations(new HashMap(), useCache, silent);
   }

   public List<T> getImplementations(Map<String, Object> configParameters) throws TechnicalConnectorException {
      return this.getImplementations(configParameters, true);
   }

   public List<T> getImplementations(Map<String, Object> configParameters, boolean useCache) throws TechnicalConnectorException {
      return this.getImplementations(configParameters, useCache, true);
   }

   public List<T> getImplementations(Map<String, Object> configParameters, boolean useCache, boolean silent) throws TechnicalConnectorException {
      List<T> result = new ArrayList();
      if (this.config.hasMatchingProperty(this.classPropertyName)) {
         List<String> headerClasses = this.config.getMatchingProperties(this.classPropertyName);
         Iterator i$ = headerClasses.iterator();

         while(i$.hasNext()) {
            String headerClassName = (String)i$.next();
            T resultItem = this.getImplementation(headerClassName, configParameters, useCache, silent);
            if (resultItem != null) {
               result.add(resultItem);
            }
         }
      } else {
         T resultItem = this.getImplementation(configParameters, useCache, silent);
         if (resultItem != null) {
            result.add(resultItem);
         }
      }

      return result;
   }

   private void init(T result, Map<String, Object> configParameters, boolean silent) throws TechnicalConnectorException {
      try {
         if (result instanceof ConfigurableImplementation) {
            if (configParameters == null) {
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"addConfigParamsIfNeeded : parameter configParameters is null!"});
            }

            ConfigurableImplementation resultAsConfigurable = (ConfigurableImplementation)result;
            resultAsConfigurable.initialize(configParameters);
         } else if (configParameters != null && !configParameters.isEmpty()) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"non configurable implementation " + result.getClass() + " called with non empty configParameters : the class should implement the interface ConfigurableImplementation to use configParameters with the ConfigurableFactoryHelper!"});
         }

      } catch (TechnicalConnectorException var5) {
         if (!silent) {
            throw var5;
         } else {
            throw new SilentInstantiationException(var5);
         }
      }
   }

   public void invalidateCache() {
      this.cache.clear();
   }

   private static class CacheKey {
      private String className;
      private Map<String, Object> configProperties;

      public CacheKey(Map<String, Object> configProperties, String className) {
         this.configProperties = configProperties;
         this.className = className;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            ConfigurableFactoryHelper.CacheKey other = (ConfigurableFactoryHelper.CacheKey)obj;
            if (this.className == null) {
               if (other.className != null) {
                  return false;
               }
            } else if (!this.className.equals(other.className)) {
               return false;
            }

            if (this.configProperties == null) {
               if (other.configProperties != null) {
                  return false;
               }
            } else if (!this.configProperties.equals(other.configProperties)) {
               return false;
            }

            return true;
         }
      }

      public int hashCode() {
         int result = 1;
         result = 31 * result + (this.className == null ? 0 : this.className.hashCode());
         result = 31 * result + (this.configProperties == null ? 0 : this.configProperties.hashCode());
         return result;
      }
   }
}
