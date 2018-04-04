package org.taktik.connector.business.hubv3.builders;

import org.taktik.connector.business.hubv3.builders.impl.RequestBuilderImpl;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class RequestBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "hubv3.requestbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "org.taktik.connector.business.hubv3.builders.impl.RequestBuilderImpl";
   private static ConfigurableFactoryHelper<RequestBuilderImpl> helperFactoryRequestBuilder = new ConfigurableFactoryHelper("hubv3.requestbuilder.class", "org.taktik.connector.business.hubv3.builders.impl.RequestBuilderImpl");

   public static RequestBuilderImpl getRequestBuilder() throws TechnicalConnectorException {
      return (RequestBuilderImpl)helperFactoryRequestBuilder.getImplementation();
   }
}
