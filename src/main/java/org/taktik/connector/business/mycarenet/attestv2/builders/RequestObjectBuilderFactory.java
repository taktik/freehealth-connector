package org.taktik.connector.business.mycarenet.attestv2.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class RequestObjectBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "attestv2.requestobjectbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "org.taktik.connector.business.mycarenet.attestv2.builders.impl.RequestObjectBuilderImpl";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryrequestBuilder = new ConfigurableFactoryHelper("attestv2.requestobjectbuilder.class", "org.taktik.connector.business.mycarenet.attestv2.builders.impl.RequestObjectBuilderImpl");

   private RequestObjectBuilderFactory() {
   }

   public static RequestObjectBuilder getRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestObjectBuilder)helperFactoryrequestBuilder.getImplementation();
   }
}
