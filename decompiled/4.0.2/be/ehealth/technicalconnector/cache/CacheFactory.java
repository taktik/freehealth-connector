package be.ehealth.technicalconnector.cache;

import be.ehealth.technicalconnector.cache.impl.HashMapCache;
import be.ehealth.technicalconnector.cache.impl.JSR107Cache;
import be.ehealth.technicalconnector.exception.ConfigurationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.Duration;

public final class CacheFactory {
   private static final String PROP_CACHE_MEMORY_IMPL = "be.ehealth.technicalconnector.cache.memory.impl";
   private static final String DEFAULT_CACHE_MEMORY_IMPL = HashMapCache.class.getName();
   private static final String PROP_CACHE_PERSISTENT_IMPL = "be.ehealth.technicalconnector.cache.persistent.impl";
   private static final String DEFAULT_CACHE_PERSISTENT_IMPL = JSR107Cache.class.getName();

   public CacheFactory() {
   }

   public static <K, V> Cache<K, V> newInstance(CacheType cacheType, String cacheName, CacheInformation.ExpiryType expiryType, Duration expiryDuration) {
      try {
         ConfigurableFactoryHelper helper;
         switch (cacheType) {
            case MEMORY:
               helper = new ConfigurableFactoryHelper("be.ehealth.technicalconnector.cache.memory.impl", DEFAULT_CACHE_MEMORY_IMPL);
               break;
            case PERSISTENT:
               helper = new ConfigurableFactoryHelper("be.ehealth.technicalconnector.cache.persistent.impl", DEFAULT_CACHE_PERSISTENT_IMPL);
               break;
            default:
               throw new IllegalArgumentException("Unsupported cache type [" + cacheType + "]");
         }

         Map<String, Object> options = new HashMap();
         options.put("cacheName", cacheName);
         if (expiryType != null) {
            options.put("cacheExpiryType", CacheFactory.ExpiryType.valueOf(expiryType.name()));
         }

         options.put("cacheExpiryDuration", expiryDuration);
         return (Cache)helper.getImplementation(options);
      } catch (TechnicalConnectorException var6) {
         throw new ConfigurationException(var6);
      }
   }

   public static enum ExpiryType {
      NONE,
      TTL;

      private ExpiryType() {
      }
   }

   public static enum CacheType {
      PERSISTENT,
      MEMORY;

      private CacheType() {
      }
   }
}
