package be.ehealth.businessconnector.genericasync.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;

public final class BuilderFactory {
   public static final String PROP_RESPONSEBUILDER_CLASS = "genericasync.responseobjectbuilder.class";
   private static final String PROP_REQUESTBUILDER_CLASS_SUFFIX = ".genericasync.requestobjectbuilder.class";
   private static final String DEFAULT_RESPONSEBUILDER_CLASS = "be.ehealth.businessconnector.genericasync.builders.impl.ResponseObjectBuilderImpl";
   private static final String DEFAULT_REQUESTBUILDER_CLASS = "be.ehealth.businessconnector.genericasync.builders.impl.RequestObjectBuilderImpl";
   private static BuilderConfig requestBuilderConfig = new BuilderConfig(".genericasync.requestobjectbuilder.class", "be.ehealth.businessconnector.genericasync.builders.impl.RequestObjectBuilderImpl");
   private static ConfigurableFactoryHelper<ResponseObjectBuilder> helperFactoryresponseBuilder = new ConfigurableFactoryHelper("genericasync.responseobjectbuilder.class", "be.ehealth.businessconnector.genericasync.builders.impl.ResponseObjectBuilderImpl");

   private BuilderFactory() {
   }

   private static <T> T getBuilder(BuilderConfig builderConfig, String serviceName) throws TechnicalConnectorException {
      ConfigurableFactoryHelper<T> helper = new ConfigurableFactoryHelper("genericasync." + serviceName + builderConfig.componentName, builderConfig.defaultBuilderClassName);
      Map<String, Object> initParams = new HashMap();
      initParams.put("servicename", serviceName);
      return helper.getImplementation(initParams, true);
   }

   public static RequestObjectBuilder getRequestObjectBuilder(String serviceName) throws TechnicalConnectorException {
      return (RequestObjectBuilder)getBuilder(requestBuilderConfig, serviceName);
   }

   public static ResponseObjectBuilder getResponseObjectBuilder() throws TechnicalConnectorException {
      return (ResponseObjectBuilder)helperFactoryresponseBuilder.getImplementation();
   }

   private static class BuilderConfig {
      private String componentName;
      private String defaultBuilderClassName;

      public BuilderConfig(String componentName, String defaultBuilderClassName) {
         this.componentName = componentName;
         this.defaultBuilderClassName = defaultBuilderClassName;
      }
   }
}
