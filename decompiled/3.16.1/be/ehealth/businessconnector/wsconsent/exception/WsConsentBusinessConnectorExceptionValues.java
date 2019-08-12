package be.ehealth.businessconnector.wsconsent.exception;

public enum WsConsentBusinessConnectorExceptionValues {
   REQUIRED_FIELD_NULL("required.field.null", "A required field is missing : {1}");

   private String errorCode;
   private String message;

   private WsConsentBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
