package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.config.util.ConfigUtil;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleClassloader implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleClassloader.class);
   private ClassLoader oldContextClassLoader;

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      if (ConfigUtil.isNet()) {
         LOG.info("Changing current classloader");
         this.oldContextClassLoader = Thread.currentThread().getContextClassLoader();
         Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
      }

   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
      if (this.oldContextClassLoader != null) {
         LOG.info("Resetting classloader");
         Thread.currentThread().setContextClassLoader(this.oldContextClassLoader);
      }

   }
}
