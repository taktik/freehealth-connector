package be.ehealth.technicalconnector.exception;

public enum UnsealConnectorExceptionValues {
   ERROR_CRYPTO("error.crypto", "Error while trying to (un)seal: {0}");

   private String errorCode;
   private String message;

   private UnsealConnectorExceptionValues(String errorCode, String errorMessage) {
      this.errorCode = errorCode;
      this.message = errorMessage;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public String getMessage() {
      return this.message;
   }
}
