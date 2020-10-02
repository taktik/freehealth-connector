package be.ehealth.technicalconnector.ws;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.util.HashMap;

public final class ServiceFactory {
   private static final String DEFAULT_CLASSNAME = "be.ehealth.technicalconnector.ws.impl.GenericWsSenderImpl";
   private static final String PROP_CLASSNAME_IMPL = "connector.genericwssender.implementation.class";
   private static ConfigurableFactoryHelper<GenericWsSender> factory = new ConfigurableFactoryHelper("connector.genericwssender.implementation.class", "be.ehealth.technicalconnector.ws.impl.GenericWsSenderImpl");

   private ServiceFactory() {
      throw new UnsupportedOperationException();
   }

   public static GenericWsSender getGenericWsSender() throws TechnicalConnectorException {
      return (GenericWsSender)factory.getImplementation(new HashMap(), false);
   }
}
