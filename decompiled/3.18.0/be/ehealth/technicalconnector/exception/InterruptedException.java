package be.ehealth.technicalconnector.exception;

public class InterruptedException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public InterruptedException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
