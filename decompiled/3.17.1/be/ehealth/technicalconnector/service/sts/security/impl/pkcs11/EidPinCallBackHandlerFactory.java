package be.ehealth.technicalconnector.service.sts.security.impl.pkcs11;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import javax.security.auth.callback.CallbackHandler;

/** @deprecated */
@Deprecated
public final class EidPinCallBackHandlerFactory {
   private static final String PROP_CALLBACKHANDLER_CLASS = "callbackhandler.class";
   private static final String DEFAULT_CALLBACKHANDLER_CLASS = "com.sun.security.auth.callback.TextCallbackHandler";
   private static ConfigurableFactoryHelper<CallbackHandler> handler = new ConfigurableFactoryHelper("callbackhandler.class", "com.sun.security.auth.callback.TextCallbackHandler", CallbackHandler.class);

   private EidPinCallBackHandlerFactory() {
   }

   public static CallbackHandler getHandler() throws TechnicalConnectorException {
      return (CallbackHandler)handler.getImplementation();
   }
}
