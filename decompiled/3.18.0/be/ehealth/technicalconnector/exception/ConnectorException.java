package be.ehealth.technicalconnector.exception;

public class ConnectorException extends Exception {
   private static final long serialVersionUID = 1L;
   private final String errorCode;

   protected ConnectorException(String message, String errorCode) {
      super(message);
      this.errorCode = errorCode;
   }

   protected ConnectorException(String message, String errorCode, Throwable cause) {
      super(message, cause);
      this.errorCode = errorCode;
   }

   public String getErrorCode() {
      return this.errorCode;
   }
}
