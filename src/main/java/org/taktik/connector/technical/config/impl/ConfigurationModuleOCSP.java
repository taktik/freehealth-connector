package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.CryptoFactory;
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
