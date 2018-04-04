package org.taktik.connector.business.registration.builder;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class RegistrationRequestBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "registration.requestobjectbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "org.taktik.connector.business.registration.builder.impl.RequestBuilderImpl";
   private static ConfigurableFactoryHelper<RequestBuilder> helperFactoryrequestBuilder = new ConfigurableFactoryHelper("registration.requestobjectbuilder.class", "org.taktik.connector.business.registration.builder.impl.RequestBuilderImpl");

   public static RequestBuilder getRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestBuilder)helperFactoryrequestBuilder.getImplementation();
   }
}
