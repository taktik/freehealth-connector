package be.ehealth.technicalconnector.shutdown;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShutdownRegistry {
   private static final String SHUTDOWNHOOK_ACTIVATED = "be.ehealth.technicalconnector.shutdown.runtime.shutdownhook";

   public static void register(ShutdownHook hook) {
      ShutdownRegistry.ShutdownRegistryEnum.INSTANCE.register(hook);
   }

   public static void shutdown() {
      ShutdownRegistry.ShutdownRegistryEnum.INSTANCE.shutdown();
   }

   static enum ShutdownRegistryEnum {
      INSTANCE(new ArrayList());

      private List<ShutdownHook> registry;

      private ShutdownRegistryEnum(List<ShutdownHook> registry) {
         this.registry = registry;
         if (Boolean.parseBoolean(System.getProperty("be.ehealth.technicalconnector.shutdown.runtime.shutdownhook", "true"))) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
               public void run() {
                  ShutdownRegistryEnum.this.shutdown();
               }
            });
         }

      }

      void register(ShutdownHook hook) {
         this.registry.add(hook);
      }

      void shutdown() {
         Iterator i$ = this.registry.iterator();

         while(i$.hasNext()) {
            ShutdownHook hook = (ShutdownHook)i$.next();
            hook.shutdown();
         }

      }
   }
}
