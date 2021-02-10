package be.ehealth.technicalconnector.mapper;

public interface Mapper {
   String MAPPING_FILES = "be.ehealth.technicalconnector.mapper.configfiles";

   <T> T map(Object var1, Class<T> var2);
}
