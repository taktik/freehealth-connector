package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

public class ConfigurationModuleSysOut implements ConfigurationModule {
   public static final String ENABLE_SYSOUT_REDIRECT = "configmodule.enable.sysout.redirect";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleSysOut.class);

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      if ("true".equals(config.getProperty("configmodule.enable.sysout.redirect"))) {
         SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
      } else {
         SysOutOverSLF4J.stopSendingSystemOutAndErrToSLF4J();
         SysOutOverSLF4J.restoreOriginalSystemOutputs();
      }

   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
      SysOutOverSLF4J.stopSendingSystemOutAndErrToSLF4J();
      SysOutOverSLF4J.restoreOriginalSystemOutputs();
   }
}
