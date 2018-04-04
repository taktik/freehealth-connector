package org.taktik.connector.technical.handler;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsupportedCallbackHandler implements CallbackHandler {
   private static final Logger LOG = LoggerFactory.getLogger(UnsupportedCallbackHandler.class);

   public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
      int len$ = callbacks.length;
      int i$ = 0;
      if (i$ < len$) {
         Callback callback = callbacks[i$];
         LOG.warn("Unable to handle callback: " + callback.getClass());
         throw new UnsupportedCallbackException(callback, "Unable to handle callback: " + callback.getClass());
      }
   }
}
