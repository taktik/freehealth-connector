package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.Map;

public interface ConfigurableImplementation {
   void initialize(Map<String, Object> var1) throws TechnicalConnectorException;
}
