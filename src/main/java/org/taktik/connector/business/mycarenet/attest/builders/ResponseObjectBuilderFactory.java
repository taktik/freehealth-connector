package org.taktik.connector.business.mycarenet.attest.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class ResponseObjectBuilderFactory {
   public static final String PROP_RESPONSEBUILDER_CLASS = "attest.responseobjectbuilder.class";
   public static final String DEFAULT_RESPONSEBUILDER_CLASS = "org.taktik.connector.business.mycarenet.attest.builders.impl.ResponseObjectBuilderImpl";
   private static ConfigurableFactoryHelper<ResponseObjectBuilder> helperFactoryresponseBuilder = new ConfigurableFactoryHelper("attest.responseobjectbuilder.class", "org.taktik.connector.business.mycarenet.attest.builders.impl.ResponseObjectBuilderImpl");

   public static ResponseObjectBuilder getResponseObjectBuilder() throws TechnicalConnectorException {
      return (ResponseObjectBuilder)helperFactoryresponseBuilder.getImplementation();
   }
}
