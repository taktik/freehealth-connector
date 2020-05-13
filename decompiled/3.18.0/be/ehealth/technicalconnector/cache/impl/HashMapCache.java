package be.ehealth.technicalconnector.cache.impl;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import java.util.HashMap;
import java.util.Map;

public class HashMapCache<K, V> implements Cache<K, V>, ConfigurableImplementation {
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

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
   }
}
