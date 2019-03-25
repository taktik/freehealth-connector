package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.CryptoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleOCSP implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleOCSP.class);

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      CryptoFactory.resetOCSPOptions();
   }

   public void unload() throws TechnicalConnectorException {
   }
}
