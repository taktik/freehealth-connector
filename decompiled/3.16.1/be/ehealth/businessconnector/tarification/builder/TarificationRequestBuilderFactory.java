package be.ehealth.businessconnector.tarification.builder;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class TarificationRequestBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "tarification.requestobjectbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "be.ehealth.businessconnector.tarification.builder.impl.RequestBuilderImpl";
   private static ConfigurableFactoryHelper<RequestBuilder> helperFactoryrequestBuilder = new ConfigurableFactoryHelper("tarification.requestobjectbuilder.class", "be.ehealth.businessconnector.tarification.builder.impl.RequestBuilderImpl");

   public static RequestBuilder getRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestBuilder)helperFactoryrequestBuilder.getImplementation();
   }
}
