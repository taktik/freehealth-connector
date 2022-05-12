package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor;

public class ConfigurationModuleEndpointDistributor implements ConfigurationModule {
   private static final String SYSPROP_ENDPOINT_DISTRIBUTOR_ACTIVE = "be.fgov.ehealth.technicalconnector.bootstrap.bcp.endpointupdater.active";

   public ConfigurationModuleEndpointDistributor() {
   }

   public void init(Configuration config) throws TechnicalConnectorException {
      if ("true".equals(System.getProperty("be.fgov.ehealth.technicalconnector.bootstrap.bcp.endpointupdater.active", "true"))) {
         EndpointDistributor.update();
      }

   }

   public void unload() throws TechnicalConnectorException {
   }
}
