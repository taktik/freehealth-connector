package org.taktik.connector.technical.mapper.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.mapper.Mapper;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import java.util.Arrays;
import java.util.Map;

public final class MapperDozerImpl implements Mapper, ConfigurableImplementation {
   //TODO use Dozer
   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {

   }

   public <T> T map(Object source, Class<T> clazz) {
      return null;
   }
}
