package be.ehealth.technicalconnector.handler;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsupportedCallbackHandler implements CallbackHandler {
   private static final Logger LOG = LoggerFactory.getLogger(UnsupportedCallbackHandler.class);

   public UnsupportedCallbackHandler() {
   }

   public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
      int var3 = callbacks.length;
      byte var4 = 0;
      if (var4 < var3) {
         Callback callback = callbacks[var4];
         LOG.warn("Unable to handle callback: {}", callback.getClass());
         throw new UnsupportedCallbackException(callback, "Unable to handle callback: " + callback.getClass());
      }
   }
}
