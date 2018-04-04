package org.taktik.connector.technical.cache;

import org.taktik.connector.technical.cache.impl.HashMapCache;
import org.taktik.connector.technical.exception.ConfigurationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class CacheFactory {
   private static final String PROP_CACHE_MEMORY_IMPL = "org.taktik.connector.technical.cache.memory.impl";
   private static final String DEFAULT_CACHE_MEMORY_IMPL = HashMapCache.class.getName();
   private static final String PROP_CACHE_PERSISTENT_IMPL = "org.taktik.connector.technical.cache.persistent.impl";
   private static final String DEFAULT_CACHE_PERSISTENT_IMPL = HashMapCache.class.getName();

   public static <K, V> Cache<K, V> newInstance(CacheFactory.CacheType cacheType) {
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

         return (Cache)helper.getImplementation();
      } catch (TechnicalConnectorException var2) {
         throw new ConfigurationException(var2);
      }
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$be$ehealth$technicalconnector$cache$CacheFactory$CacheType = new int[CacheFactory.CacheType.values().length];

      static {
         try {
            $SwitchMap$be$ehealth$technicalconnector$cache$CacheFactory$CacheType[CacheFactory.CacheType.MEMORY.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$be$ehealth$technicalconnector$cache$CacheFactory$CacheType[CacheFactory.CacheType.PERSISTENT.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }

   public static enum CacheType {
      PERSISTENT,
      MEMORY;
   }
}
