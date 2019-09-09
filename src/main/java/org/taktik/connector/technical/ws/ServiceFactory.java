package org.taktik.connector.technical.ws;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.HashMap;

public final class ServiceFactory {
   private static final String DEFAULT_CLASSNAME = "org.taktik.connector.technical.ws.impl.GenericWsSenderImpl";
   private static final String PROP_CLASSNAME_IMPL = "connector.genericwssender.implementation.class";
   private static ConfigurableFactoryHelper<GenericWsSender> factory = new ConfigurableFactoryHelper("connector.genericwssender.implementation.class", "org.taktik.connector.technical.ws.impl.GenericWsSenderImpl");

   private ServiceFactory() {
      throw new UnsupportedOperationException();
   }

   public static GenericWsSender getGenericWsSender() throws TechnicalConnectorException {
      return factory.getImplementation(new HashMap(), false);
   }
}
