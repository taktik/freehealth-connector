package org.taktik.connector.technical.mapper;

public interface Mapper {
   String MAPPING_FILES = "org.taktik.connector.technical.mapper.configfiles";

   <T> T map(Object var1, Class<T> var2);
}
