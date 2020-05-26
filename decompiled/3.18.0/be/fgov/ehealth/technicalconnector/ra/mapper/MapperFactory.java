package be.fgov.ehealth.technicalconnector.ra.mapper;

public class MapperFactory {
   private MapperFactory() {
   }

   public static RaMapper mapper() {
      return new RaMapperSelmaGeneratedClass();
   }
}
