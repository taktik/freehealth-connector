package be.apb.gfddpp.validation.exception;

public class SingleMessageValidationException extends Exception {
   private static final long serialVersionUID = 1L;

   public SingleMessageValidationException(String message, Throwable cause) {
      super(message, cause);
   }

   public SingleMessageValidationException(String message) {
      super(message);
   }

   public SingleMessageValidationException(Throwable cause) {
      super(cause);
   }
}
