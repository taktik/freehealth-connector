package org.taktik.connector.technical.cache;

import org.taktik.connector.technical.cache.impl.HashMapCache;
import org.taktik.connector.technical.exception.ConfigurationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.joda.time.Duration;

public final class CacheFactory {
   private static final String PROP_CACHE_MEMORY_IMPL = "org.taktik.connector.technical.cache.memory.impl";
   private static final String DEFAULT_CACHE_MEMORY_IMPL = HashMapCache.class.getName();
   private static final String PROP_CACHE_PERSISTENT_IMPL = "org.taktik.connector.technical.cache.persistent.impl";
   private static final String DEFAULT_CACHE_PERSISTENT_IMPL = HashMapCache.class.getName();

   public static <K, V> Cache<K, V> newInstance(CacheFactory.CacheType cacheType, String cacheName, CacheInformation.ExpiryType expiryType, Duration expiryDuration) {
      try {
         ConfigurableFactoryHelper helper;
         switch(cacheType) {
         case MEMORY:
            helper = new ConfigurableFactoryHelper("org.taktik.connector.technical.cache.memory.impl", DEFAULT_CACHE_MEMORY_IMPL);
            break;
         case PERSISTENT:
            helper = new ConfigurableFactoryHelper("org.taktik.connector.technical.cache.persistent.impl", DEFAULT_CACHE_PERSISTENT_IMPL);
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

   /** @deprecated */
   @Deprecated
   public static <K, V> Cache<K, V> newInstance(CacheFactory.CacheType cacheType, String serviceName) {
      return newInstance(cacheType, UUID.randomUUID().toString(), CacheInformation.ExpiryType.NONE, (Duration)null);
         }

   public static enum ExpiryType {
      NONE,
      TTL;
   }

   public static enum CacheType {
      PERSISTENT,
      MEMORY;
   }
}
