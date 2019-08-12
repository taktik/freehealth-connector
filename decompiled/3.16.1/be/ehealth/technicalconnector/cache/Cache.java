package be.ehealth.technicalconnector.cache;

import java.util.Map;

public interface Cache<K, V> {
   String CACHE_NAME = "cacheName";
   String CACHE_EXPIRY_TYPE = "cacheExpiryType";
   String CACHE_EXPIRY_DURATION = "cacheExpiryDuration";

   String getName();

   V get(K var1);

   boolean containsKey(K var1);

   void put(K var1, V var2);

   void remove(K var1);

   void clear();

   void putAll(Map<K, V> var1);
}
