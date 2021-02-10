package be.ehealth.technicalconnector.config;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public interface ConfigurationModule {
   void init(Configuration var1) throws TechnicalConnectorException;

   void unload() throws TechnicalConnectorException;
}
