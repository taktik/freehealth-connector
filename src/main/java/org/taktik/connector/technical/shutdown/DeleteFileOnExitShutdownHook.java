package org.taktik.connector.technical.shutdown;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteFileOnExitShutdownHook implements ShutdownHook {
   public static final String SYS_PROP_DELETE_ON_EXIT = "org.taktik.connector.technical.shutdown.deletefileonexit.activated";
   private DeleteOnExit hook;

   public static void execute() {
      DeleteFileOnExitShutdownHookEnum.INSTANCE.getShutdownHook().shutdown();
   }

   public static void deleteOnExit(File file) {
      DeleteFileOnExitShutdownHookEnum.INSTANCE.getShutdownHook().hook.deleteOnExit(file);
   }

   private DeleteFileOnExitShutdownHook(DeleteOnExit hook) {
      this.hook = hook;
   }

   public void shutdown() {
      this.hook.shutdown();
   }

   // $FF: synthetic method
   DeleteFileOnExitShutdownHook(DeleteOnExit x0, Object x1) {
      this(x0);
   }

   private static class CustomDeleteOnExit implements DeleteOnExit {
      private List<File> deleteOnExitFiles;

      private CustomDeleteOnExit() {
         this.deleteOnExitFiles = new ArrayList();
      }

      public void deleteOnExit(File file) {
         this.deleteOnExitFiles.add(file);
      }

      public void shutdown() {
         Iterator i$ = this.deleteOnExitFiles.iterator();

         while(i$.hasNext()) {
            File file = (File)i$.next();
            file.delete();
         }

      }

      // $FF: synthetic method
      CustomDeleteOnExit(Object x0) {
         this();
      }
   }

   private static class JVMDeleteOnExit implements DeleteOnExit {
      private JVMDeleteOnExit() {
      }

      public void deleteOnExit(File file) {
         file.deleteOnExit();
      }

      public void shutdown() {
      }

      // $FF: synthetic method
      JVMDeleteOnExit(Object x0) {
         this();
      }
   }

   private interface DeleteOnExit {
      void deleteOnExit(File var1);

      void shutdown();
   }

   private static enum DeleteFileOnExitShutdownHookEnum {
      INSTANCE;

      private DeleteFileOnExitShutdownHook shutdownHook;

      private DeleteFileOnExitShutdownHookEnum() {
         if ("true".equals(System.getProperty("org.taktik.connector.technical.shutdown.deletefileonexit.activated", "false"))) {
            this.shutdownHook = new DeleteFileOnExitShutdownHook(new CustomDeleteOnExit());
         } else {
            this.shutdownHook = new DeleteFileOnExitShutdownHook(new JVMDeleteOnExit());
         }

         ShutdownRegistry.register(this.shutdownHook);
      }

      public DeleteFileOnExitShutdownHook getShutdownHook() {
         return this.shutdownHook;
      }
   }
}
