package be.ehealth.businessconnector.therlink.mappers;

public final class MapperFactory {
   private static RequestObjectMapper requestObjectMapper = new RequestObjectMapper();
   private static ResponseObjectMapper responseObjectMapper = new ResponseObjectMapper();
   private static HcPartyMapper hcPartyMapper = new HcPartyMapper();

   private MapperFactory() {
   }

   public static RequestObjectMapper getRequestObjectMapper() {
      return requestObjectMapper;
   }

   public static ResponseObjectMapper getResponseObjectMapper() {
      return responseObjectMapper;
   }

   public static HcPartyMapper getHcPartyMapper() {
      return hcPartyMapper;
   }
}
