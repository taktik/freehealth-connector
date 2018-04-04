package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.util.Map;

public interface ConfigurableImplementation {
   void initialize(Map<String, Object> var1) throws TechnicalConnectorException;
}
