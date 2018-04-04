package org.taktik.connector.business.dmg.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class ResponseObjectBuilderFactory {
   public static final String PROP_RESPONSEBUILDER_CLASS = "dmg.responseobjectbuilder.class";
   public static final String DEFAULT_RESPONSEBUILDER_CLASS = "org.taktik.connector.business.dmg.builders.impl.ResponseObjectBuilderImpl";
   private static ConfigurableFactoryHelper<ResponseObjectBuilder> helperFactoryresponseBuilder = new ConfigurableFactoryHelper("dmg.responseobjectbuilder.class", "org.taktik.connector.business.dmg.builders.impl.ResponseObjectBuilderImpl");

   public static ResponseObjectBuilder getResponseObjectBuilder() throws TechnicalConnectorException {
      return helperFactoryresponseBuilder.getImplementation();
   }
}
