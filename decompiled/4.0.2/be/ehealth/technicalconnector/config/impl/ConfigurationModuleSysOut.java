package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

public class ConfigurationModuleSysOut implements ConfigurationModule {
   public static final String ENABLE_SYSOUT_REDIRECT = "configmodule.enable.sysout.redirect";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleSysOut.class);

   public ConfigurationModuleSysOut() {
   }

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      if ("true".equals(config.getProperty("configmodule.enable.sysout.redirect"))) {
         SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
      } else {
         SysOutOverSLF4J.stopSendingSystemOutAndErrToSLF4J();
         SysOutOverSLF4J.restoreOriginalSystemOutputs();
      }

   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule {}", this.getClass().getName());
      SysOutOverSLF4J.stopSendingSystemOutAndErrToSLF4J();
      SysOutOverSLF4J.restoreOriginalSystemOutputs();
   }
}
