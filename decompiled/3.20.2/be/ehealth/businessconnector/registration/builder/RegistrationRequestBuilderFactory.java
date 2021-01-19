package be.ehealth.businessconnector.registration.builder;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class RegistrationRequestBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "registration.requestobjectbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "be.ehealth.businessconnector.registration.builder.impl.RequestBuilderImpl";
   private static ConfigurableFactoryHelper<RequestBuilder> helperFactoryrequestBuilder = new ConfigurableFactoryHelper("registration.requestobjectbuilder.class", "be.ehealth.businessconnector.registration.builder.impl.RequestBuilderImpl");

   private RegistrationRequestBuilderFactory() {
   }

   public static RequestBuilder getRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestBuilder)helperFactoryrequestBuilder.getImplementation();
   }
}
