package org.taktik.connector.technical.exception;

public class InterruptedException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public InterruptedException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
