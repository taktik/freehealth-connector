package org.taktik.connector.technical.cache.impl;

import org.taktik.connector.technical.cache.Cache;
import java.util.HashMap;
import java.util.Map;

public class HashMapCache<K, V> implements Cache<K, V> {
   private static final String NAME = "HashMapCache";
   private HashMap<K, V> cache = new HashMap();

   public String getName() {
      return "HashMapCache";
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
}
