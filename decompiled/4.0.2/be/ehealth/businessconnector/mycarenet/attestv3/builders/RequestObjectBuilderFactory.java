package be.ehealth.businessconnector.mycarenet.attestv3.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class RequestObjectBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "attestv3.requestobjectbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.attestv3.builders.impl.RequestObjectBuilderImpl";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryrequestBuilder = new ConfigurableFactoryHelper("attestv3.requestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.attestv3.builders.impl.RequestObjectBuilderImpl");

   private RequestObjectBuilderFactory() {
   }

   public static RequestObjectBuilder getRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestObjectBuilder)helperFactoryrequestBuilder.getImplementation();
   }
}
