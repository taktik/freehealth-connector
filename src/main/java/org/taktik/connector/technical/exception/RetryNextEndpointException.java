package org.taktik.connector.technical.exception;

import javax.xml.ws.handler.soap.SOAPMessageContext;

public class RetryNextEndpointException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private SOAPMessageContext context;

   public RetryNextEndpointException(SOAPMessageContext context) {
      super("Unable to connect to endpoint, allowing to retry next one.");
      this.context = context;
   }

   public RetryNextEndpointException(Throwable cause) {
      super("Unable to connect to endpoint, allowing to retry next one.", cause);
   }

   public boolean hasContext() {
      return this.context != null;
   }

   public SOAPMessageContext getContext() {
      return this.context;
   }
}
