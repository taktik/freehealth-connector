package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

/** @deprecated */
@Deprecated
public class ConfigurationModuleSSLVerifier implements ConfigurationModule {
   private ConfigurationModuleSSL configurationModuleSSL = new ConfigurationModuleSSL();

   public ConfigurationModuleSSLVerifier() {
   }

   public void init(Configuration config) throws TechnicalConnectorException {
      config.setProperty("connector.configurationmodule.ssl.verifcation.flavour", "passthrough");
      this.configurationModuleSSL.init(config);
   }

   public void unload() throws TechnicalConnectorException {
      this.configurationModuleSSL.unload();
   }
}
