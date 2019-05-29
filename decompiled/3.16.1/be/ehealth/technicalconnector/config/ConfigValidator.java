package be.ehealth.technicalconnector.config;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.List;

public interface ConfigValidator extends Configuration {
   Configuration getConfig() throws TechnicalConnectorException;

   boolean isValid() throws TechnicalConnectorException;

   List<Object> getUnfoundPropertiesAfterValidation() throws TechnicalConnectorException;

   void invalidateCache();
}
