package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor;

public class ConfigurationModuleEndpointDistributor implements ConfigurationModule {
   private static final String SYSPROP_ENDPOINT_DISTRIBUTOR_ACTIVE = "be.fgov.ehealth.technicalconnector.bootstrap.bcp.endpointupdater.active";

   public void init(Configuration config) throws TechnicalConnectorException {
      if ("true".equals(System.getProperty("be.fgov.ehealth.technicalconnector.bootstrap.bcp.endpointupdater.active", "true"))) {
         EndpointDistributor.update();
      }

   }

   public void unload() throws TechnicalConnectorException {
   }
}
