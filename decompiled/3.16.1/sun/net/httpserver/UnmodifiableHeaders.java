package sun.net.httpserver;

import com.sun.net.httpserver.Headers;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

class UnmodifiableHeaders extends Headers {
   Headers map;

   UnmodifiableHeaders(Headers map) {
      this.map = map;
   }

   public int size() {
      return this.map.size();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public boolean containsKey(Object key) {
      return this.map.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.map.containsValue(value);
   }

   public List<String> get(Object key) {
      return this.map.get(key);
   }

   public String getFirst(String key) {
      return this.map.getFirst(key);
   }

   public List<String> put(String key, List<String> value) {
      return this.map.put(key, value);
   }

   public void add(String key, String value) {
      throw new UnsupportedOperationException("unsupported operation");
   }

   public void set(String key, String value) {
      throw new UnsupportedOperationException("unsupported operation");
   }

   public List<String> remove(Object key) {
      throw new UnsupportedOperationException("unsupported operation");
   }

   public void putAll(Map<? extends String, ? extends List<String>> t) {
      throw new UnsupportedOperationException("unsupported operation");
   }

   public void clear() {
      throw new UnsupportedOperationException("unsupported operation");
   }

   public Set<String> keySet() {
      return Collections.unmodifiableSet(this.map.keySet());
   }

   public Collection<List<String>> values() {
      return Collections.unmodifiableCollection(this.map.values());
   }

   public Set<Entry<String, List<String>>> entrySet() {
      return Collections.unmodifiableSet(this.map.entrySet());
   }

   public boolean equals(Object o) {
      return this.map.equals(o);
   }

   public int hashCode() {
      return this.map.hashCode();
   }
}
