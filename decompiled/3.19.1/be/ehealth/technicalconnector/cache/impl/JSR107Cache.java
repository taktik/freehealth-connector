package be.ehealth.technicalconnector.cache.impl;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.shutdown.ShutdownHook;
import be.ehealth.technicalconnector.shutdown.ShutdownRegistry;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import org.joda.time.Duration;

public class JSR107Cache<K, V> implements Cache<K, V>, ConfigurableImplementation, ShutdownHook {
   private javax.cache.Cache<K, V> cache;
   private CacheManager cacheManager;

   public JSR107Cache() {
      this.cacheManager = JSR107Cache.CacheImpl.INSTANCE.getCacheManager();
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      try {
         String serviceName = (String)parameterMap.get("cacheName");
         CacheFactory.ExpiryType expiryType = (CacheFactory.ExpiryType)parameterMap.get("cacheExpiryType");
         Duration expiryDuration = (Duration)parameterMap.get("cacheExpiryDuration");
         String cacheName = serviceName.replaceAll(":", "_");
         this.cache = this.cacheManager.getCache(cacheName, String.class, String.class);
         if (this.cache == null) {
            MutableConfiguration<K, V> configuration = (new MutableConfiguration()).setStoreByValue(true).setStatisticsEnabled(true).setTypes(String.class, String.class);
            switch(expiryType) {
            case NONE:
               configuration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(javax.cache.expiry.Duration.ETERNAL));
               break;
            case TTL:
               configuration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new javax.cache.expiry.Duration(TimeUnit.MILLISECONDS, expiryDuration.getMillis())));
            }

            this.cache = this.cacheManager.createCache(cacheName, configuration);
         }
      } catch (Exception var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, var7, new Object[]{"unable to instantiate JSR107 cache"});
      }

      ShutdownRegistry.register(this);
   }

   public String getName() {
      return this.cache.getName();
   }

   public V get(K key) {
      return this.cache.get(key);
   }

   public boolean containsKey(K key) {
      return this.cache.containsKey(key);
   }

   public void put(K key, V value) {
      this.cache.put(key, value);
   }

   public void remove(K key) {
      this.cache.remove(key);
   }

   public void clear() {
      this.cache.clear();
   }

   public void putAll(Map<K, V> map) {
      this.cache.putAll(map);
   }

   public void shutdown() {
      this.cacheManager.close();
   }

   private static enum CacheImpl {
      INSTANCE;

      public CacheManager getCacheManager() {
         try {
            return Caching.getCachingProvider().getCacheManager(this.getClass().getResource("/jsr107/ehcache.xml").toURI(), JSR107Cache.class.getClassLoader());
         } catch (URISyntaxException var2) {
            throw new IllegalArgumentException(var2);
         }
      }
   }
}
