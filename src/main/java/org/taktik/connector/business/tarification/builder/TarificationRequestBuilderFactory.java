package org.taktik.connector.business.tarification.builder;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class TarificationRequestBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "tarification.requestobjectbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "org.taktik.connector.business.tarification.builder.impl.RequestBuilderImpl";
   private static ConfigurableFactoryHelper<RequestBuilder> helperFactoryrequestBuilder = new ConfigurableFactoryHelper("tarification.requestobjectbuilder.class", "org.taktik.connector.business.tarification.builder.impl.RequestBuilderImpl");

   public static RequestBuilder getRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestBuilder)helperFactoryrequestBuilder.getImplementation();
   }
}
