package org.taktik.connector.technical.shutdown;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShutdownRegistry {
   private static List<ShutdownHook> registry = new ArrayList();

   public static void register(ShutdownHook hook) {
      registry.add(hook);
   }

   public static void shutdown() {
      Iterator i$ = registry.iterator();

      while(i$.hasNext()) {
         ShutdownHook hook = (ShutdownHook)i$.next();
         hook.shutdown();
      }

   }
}
