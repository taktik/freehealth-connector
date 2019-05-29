package be.ehealth.businessconnector.mycarenet.memberdata.exception;

public enum MemberDataBusinessConnectorExceptionValues {
   PARAMETER_NULL("parameters.null", "This parameter is null : {0}");

   private String errorCode;
   private String message;

   private MemberDataBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
