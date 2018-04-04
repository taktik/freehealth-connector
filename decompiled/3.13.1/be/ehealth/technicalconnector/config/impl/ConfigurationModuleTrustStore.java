package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.shutdown.ShutdownHook;
import be.ehealth.technicalconnector.shutdown.ShutdownRegistry;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleTrustStore implements ConfigurationModule {
   public static final String SYSPROP_AUTOUPDATE_ACTIVE = "be.fgov.ehealth.technicalconnector.bootstrap.tsl.autoupdater.active";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleTrustStore.class);

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      ConfigurationModuleTrustStore.TrustStoreTimerEnum.TSL.name();
   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static class TrustStoreTimerTask extends TimerTask {
      private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleTrustStore.TrustStoreTimerTask.class);

      private TrustStoreTimerTask() {
      }

      public void run() {
         try {
            MethodUtils.invokeStaticMethod(Class.forName("be.fgov.ehealth.technicalconnector.bootstrap.tsl.TrustStoreUpdater"), "launch", new Object[0]);
         } catch (Exception var2) {
            LOG.error("Unable to update truststores", var2);
         }

      }

      // $FF: synthetic method
      TrustStoreTimerTask(ConfigurationModuleTrustStore.SyntheticClass_1 x0) {
         this();
      }
   }

   private static class TrustStoreTimer implements ShutdownHook {
      private Timer timer;

      TrustStoreTimer() {
         if ("true".equals(System.getProperty("be.fgov.ehealth.technicalconnector.bootstrap.tsl.autoupdater.active", "true"))) {
            this.timer = new Timer(true);
            this.timer.schedule(new ConfigurationModuleTrustStore.TrustStoreTimerTask(), new Date(), TimeUnit.MILLISECONDS.convert(1L, TimeUnit.DAYS));
            ShutdownRegistry.register(this);
         }

      }

      public void shutdown() {
         if (this.timer != null) {
            this.timer.cancel();
         }

      }
   }

   private static enum TrustStoreTimerEnum {
      TSL(new ConfigurationModuleTrustStore.TrustStoreTimer());

      private ShutdownHook obj;

      private TrustStoreTimerEnum(ShutdownHook obj) {
         this.obj = obj;
      }

      public ShutdownHook getHook() {
         return this.obj;
      }
   }
}
