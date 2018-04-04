package org.taktik.connector.business.genericasync.builders;

import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;

public final class BuilderFactory {
   public static final String PROP_RESPONSEBUILDER_CLASS = "genericasync.responseobjectbuilder.class";
   private static final String PROP_REQUESTBUILDER_CLASS_SUFFIX = ".genericasync.requestobjectbuilder.class";
   private static final String DEFAULT_RESPONSEBUILDER_CLASS = "org.taktik.connector.business.genericasync.builders.impl.ResponseObjectBuilderImpl";
   private static final String DEFAULT_REQUESTBUILDER_CLASS = "org.taktik.connector.business.genericasync.builders.impl.RequestObjectBuilderImpl";
   private static BuilderFactory.BuilderConfig requestBuilderConfig = new BuilderFactory.BuilderConfig(".genericasync.requestobjectbuilder.class", "org.taktik.connector.business.genericasync.builders.impl.RequestObjectBuilderImpl");
   private static ConfigurableFactoryHelper<ResponseObjectBuilder> helperFactoryresponseBuilder = new ConfigurableFactoryHelper("genericasync.responseobjectbuilder.class", "org.taktik.connector.business.genericasync.builders.impl.ResponseObjectBuilderImpl");

   private static <T> T getBuilder(BuilderFactory.BuilderConfig builderConfig, String serviceName) throws TechnicalConnectorException {
      ConfigurableFactoryHelper<T> helper = new ConfigurableFactoryHelper("genericasync." + serviceName + builderConfig.componentName, builderConfig.defaultBuilderClassName);
      Map<String, Object> initParams = new HashMap();
      initParams.put("servicename", serviceName);
      return helper.getImplementation(initParams, true);
   }

   public static RequestObjectBuilder getRequestObjectBuilder(String serviceName) throws GenAsyncBusinessConnectorException, TechnicalConnectorException, InstantiationException {
      return (RequestObjectBuilder)getBuilder(requestBuilderConfig, serviceName);
   }

   public static ResponseObjectBuilder getResponseObjectBuilder() throws GenAsyncBusinessConnectorException, TechnicalConnectorException, InstantiationException {
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
