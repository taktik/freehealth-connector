package be.ehealth.businessconnector.hubv3.builders;

import be.ehealth.businessconnector.hubv3.builders.impl.RequestBuilderImpl;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class RequestBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "hubv3.requestbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "be.ehealth.businessconnector.hubv3.builders.impl.RequestBuilderImpl";
   private static ConfigurableFactoryHelper<RequestBuilderImpl> helperFactoryRequestBuilder = new ConfigurableFactoryHelper("hubv3.requestbuilder.class", "be.ehealth.businessconnector.hubv3.builders.impl.RequestBuilderImpl");

   public static RequestBuilderImpl getRequestBuilder() throws TechnicalConnectorException {
      return (RequestBuilderImpl)helperFactoryRequestBuilder.getImplementation();
   }
}
