package be.ehealth.technicalconnector.exception;

public enum SessionManagementExceptionValues {
   ERROR_GENERAL("error.general", "General Error: {0}"),
   ERROR_NOSESSION("error.nosession", "No active session found"),
   ERROR_NOTOKEN("error.notoken", "No SAML token found");

   private String errorCode;
   private String message;

   private SessionManagementExceptionValues(String errorCode, String message) {
      this.errorCode = errorCode;
      this.message = message;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public String getMessage() {
      return this.message;
   }
}
