package be.ehealth.businessconnector.hub.builders;

public final class BuilderFactory {
   private static BuilderFactory instance = new BuilderFactory();

   private BuilderFactory() {
   }

   public static BuilderFactory getInstance() {
      return instance;
   }

   public ResponseBuilder getResponseBuilder() {
      return new ResponseBuilder();
   }

   public RequestBuilder getRequestBuilder() {
      return new RequestBuilder();
   }

   public RequestBuilderComplete getRequestBuilderComplete() {
      return new RequestBuilderComplete();
   }
}
