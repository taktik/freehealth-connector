package be.fgov.ehealth.schematron.exception;

public class InitialisationException extends Exception {
   private static final long serialVersionUID = 1L;

   public InitialisationException(String msg) {
      super(msg);
   }

   public InitialisationException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
