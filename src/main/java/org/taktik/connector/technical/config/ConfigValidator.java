package org.taktik.connector.technical.config;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.util.List;

public interface ConfigValidator extends Configuration {
   Configuration getConfig() throws TechnicalConnectorException;

   boolean isValid() throws TechnicalConnectorException;

   List<Object> getUnfoundPropertiesAfterValidation() throws TechnicalConnectorException;

   void invalidateCache();
}
