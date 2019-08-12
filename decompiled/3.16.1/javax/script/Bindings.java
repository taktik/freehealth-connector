package javax.script;

import java.util.Map;

public interface Bindings extends Map<String, Object> {
   Object put(String var1, Object var2);

   void putAll(Map<? extends String, ? extends Object> var1);

   boolean containsKey(Object var1);

   Object get(Object var1);

   Object remove(Object var1);
}
