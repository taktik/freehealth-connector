package be.ehealth.businessconnector.dmg.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class RequestObjectBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "dmg.requestobjectbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "be.ehealth.businessconnector.dmg.builders.impl.RequestObjectBuilderImpl";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryrequestBuilder = new ConfigurableFactoryHelper("dmg.requestobjectbuilder.class", "be.ehealth.businessconnector.dmg.builders.impl.RequestObjectBuilderImpl");

   private RequestObjectBuilderFactory() {
   }

   public static RequestObjectBuilder getRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestObjectBuilder)helperFactoryrequestBuilder.getImplementation();
   }
}
