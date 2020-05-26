package javax.script;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class SimpleBindings implements Bindings {
   private Map<String, Object> map;

   public SimpleBindings(Map<String, Object> m) {
      if (m == null) {
         throw new NullPointerException();
      } else {
         this.map = m;
      }
   }

   public SimpleBindings() {
      this(new HashMap());
   }

   public Object put(String name, Object value) {
      this.checkKey(name);
      return this.map.put(name, value);
   }

   public void putAll(Map<? extends String, ? extends Object> toMerge) {
      if (toMerge == null) {
         throw new NullPointerException("toMerge map is null");
      } else {
         Iterator i$ = toMerge.entrySet().iterator();

         while(i$.hasNext()) {
            Entry<? extends String, ? extends Object> entry = (Entry)i$.next();
            String key = (String)entry.getKey();
            this.checkKey(key);
            this.put(key, entry.getValue());
         }

      }
   }

   public void clear() {
      this.map.clear();
   }

   public boolean containsKey(Object key) {
      this.checkKey(key);
      return this.map.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.map.containsValue(value);
   }

   public Set<Entry<String, Object>> entrySet() {
      return this.map.entrySet();
   }

   public Object get(Object key) {
      this.checkKey(key);
      return this.map.get(key);
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public Set<String> keySet() {
      return this.map.keySet();
   }

   public Object remove(Object key) {
      this.checkKey(key);
      return this.map.remove(key);
   }

   public int size() {
      return this.map.size();
   }

   public Collection<Object> values() {
      return this.map.values();
   }

   private void checkKey(Object key) {
      if (key == null) {
         throw new NullPointerException("key can not be null");
      } else if (!(key instanceof String)) {
         throw new ClassCastException("key should be a String");
      } else if (key.equals("")) {
         throw new IllegalArgumentException("key can not be empty");
      }
   }
}
