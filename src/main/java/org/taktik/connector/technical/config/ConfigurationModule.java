package org.taktik.connector.technical.config;

import org.taktik.connector.technical.exception.TechnicalConnectorException;

public interface ConfigurationModule {
   void init(Configuration var1) throws TechnicalConnectorException;

   void unload() throws TechnicalConnectorException;
}
