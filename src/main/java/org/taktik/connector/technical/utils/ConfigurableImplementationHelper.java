package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import java.util.Map;

public final class ConfigurableImplementationHelper {
   private ConfigurableImplementationHelper() {
      throw new UnsupportedOperationException();
   }

   public static <T> T get(String paramName, Map<String, Object> parameterMap, Class<T> paramType, boolean required) throws TechnicalConnectorException {
      if (parameterMap.containsKey(paramName)) {
         Object result = parameterMap.get(paramName);
         if (paramType.isAssignableFrom(result.getClass())) {
            return (T) result;
         } else {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"Expecting class [" + paramType + "] but was [" + result.getClass() + "]"});
         }
      } else if (required) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"Unable to find required param " + paramName});
      } else {
         return null;
      }
   }
}
