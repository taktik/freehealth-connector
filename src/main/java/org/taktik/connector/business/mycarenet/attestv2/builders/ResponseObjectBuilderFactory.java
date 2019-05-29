package org.taktik.connector.business.mycarenet.attestv2.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class ResponseObjectBuilderFactory {
   public static final String PROP_RESPONSEBUILDER_CLASS = "attestv2.responseobjectbuilder.class";
   public static final String DEFAULT_RESPONSEBUILDER_CLASS = "org.taktik.connector.business.mycarenet.attestv2.builders.impl.ResponseObjectBuilderImpl";
   private static ConfigurableFactoryHelper<ResponseObjectBuilder> helperFactoryresponseBuilder = new ConfigurableFactoryHelper("attestv2.responseobjectbuilder.class", "org.taktik.connector.business.mycarenet.attestv2.builders.impl.ResponseObjectBuilderImpl");

   private ResponseObjectBuilderFactory() {
   }

   public static ResponseObjectBuilder getResponseObjectBuilder() throws TechnicalConnectorException {
      return (ResponseObjectBuilder)helperFactoryresponseBuilder.getImplementation();
   }
}
