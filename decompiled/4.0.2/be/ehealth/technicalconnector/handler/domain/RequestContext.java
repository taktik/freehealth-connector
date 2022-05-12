package be.ehealth.technicalconnector.handler.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestContext {
   private Map<String, Object> context = new HashMap();
   private static RequestContext instance;

   public RequestContext() {
   }

   public static RequestContext getInstance() {
      if (instance == null) {
         instance = new RequestContext();
      }

      return instance;
   }

   public void clear() {
      this.context.clear();
   }

   public void put(String key, Object value) {
      this.context.put(key, value);
   }

   public Object get(String key) {
      return this.context.get(key);
   }

   public Set<String> keySet() {
      return this.context.keySet();
   }

   public boolean containsKey(String key) {
      return this.context.containsKey(key);
   }
}
