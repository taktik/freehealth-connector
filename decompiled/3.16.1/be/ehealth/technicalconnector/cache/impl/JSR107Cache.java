package be.ehealth.technicalconnector.cache.impl;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.CacheFeederHandler;
import be.ehealth.technicalconnector.shutdown.ShutdownHook;
import be.ehealth.technicalconnector.shutdown.ShutdownRegistry;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.Map;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import org.apache.commons.io.IOUtils;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSR107Cache<K, V> implements Cache<K, V>, ConfigurableImplementation, ShutdownHook {
   private static final Logger LOG = LoggerFactory.getLogger(JSR107Cache.class);
   private javax.cache.Cache<K, V> cache;
   private CacheManager cacheManager;

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      try {
         String serviceName = (String)parameterMap.get("cacheName");
         CacheFactory.ExpiryType expiryType = (CacheFactory.ExpiryType)parameterMap.get("cacheExpiryType");
         Duration expiryDuration = (Duration)parameterMap.get("cacheExpiryDuration");
         URI cacheConfig = null;
         switch(expiryType) {
         case NONE:
            cacheConfig = CacheFeederHandler.class.getResource("/jsr107/ehcache-expiry-none.xml").toURI();
            break;
         case TTL:
            String xml = ConnectorIOUtils.getResourceAsString("/jsr107/ehcache-expiry-ttl.xml").replaceAll("${expiry.ttl}", "" + expiryDuration.getMillis());
            File configFile = ConnectorIOUtils.createTempFile("ehcache-expiry-ttl.xml");
            IOUtils.write(xml, new FileWriter(configFile));
            cacheConfig = configFile.toURI();
            break;
         default:
            throw new IllegalArgumentException("Unsupported expiry" + expiryType);
         }

         this.cacheManager = Caching.getCachingProvider().getCacheManager(cacheConfig, JSR107Cache.class.getClassLoader());
         this.cache = this.cacheManager.getCache(serviceName, String.class, String.class);
         if (this.cache == null) {
            MutableConfiguration configuration = (new MutableConfiguration()).setStoreByValue(true).setStatisticsEnabled(true).setTypes(String.class, String.class);
            this.cache = this.cacheManager.createCache(serviceName, configuration);
         }
      } catch (Exception var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"unable to instantiate JSR107 cache", var8});
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
}
