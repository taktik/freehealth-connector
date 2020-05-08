package be.ehealth.technicalconnector.mapper.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.mapper.Mapper;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import java.util.Arrays;
import java.util.Map;
import org.dozer.DozerBeanMapper;

public final class MapperDozerImpl implements Mapper, ConfigurableImplementation {
   private DozerBeanMapper mapper = new DozerBeanMapper();

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      this.mapper.setMappingFiles(Arrays.asList((String[])((String[])parameterMap.get("be.ehealth.technicalconnector.mapper.configfiles"))));
   }

   public <T> T map(Object source, Class<T> clazz) {
      return this.mapper.map(source, clazz);
   }
}
