package be.apb.gfddpp.common.medicationscheme.validator;

public class ValidatorException extends Exception {
   public ValidatorException() {
   }

   public ValidatorException(String message) {
      super(message);
   }

   public ValidatorException(String message, Throwable cause) {
      super(message, cause);
   }

   public ValidatorException(Throwable cause) {
      super(cause);
   }

   protected ValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
