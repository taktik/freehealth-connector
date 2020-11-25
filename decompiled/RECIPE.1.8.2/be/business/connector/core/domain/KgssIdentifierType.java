package be.business.connector.core.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum KgssIdentifierType {
   CBE("CBE"),
   SSIN("SSIN"),
   NIHII("NIHII"),
   NIHII_PHARMACY("NIHII-PHARMACY");

   private String name;
   private static Map<String, KgssIdentifierType> lookup = new HashMap();

   static {
      Iterator var1 = EnumSet.allOf(KgssIdentifierType.class).iterator();

      while(var1.hasNext()) {
         KgssIdentifierType type = (KgssIdentifierType)var1.next();
         lookup.put(type.getName(), type);
      }

   }

   private KgssIdentifierType(String name) {
      this.name = name;
   }

   public static KgssIdentifierType lookup(String name) {
      return lookup.containsKey(name) ? (KgssIdentifierType)lookup.get(name) : null;
   }

   public String getName() {
      return this.name;
   }
}
