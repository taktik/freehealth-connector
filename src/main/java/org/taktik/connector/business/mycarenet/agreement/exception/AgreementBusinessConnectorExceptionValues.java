package org.taktik.connector.business.mycarenet.agreement.exception;

public enum AgreementBusinessConnectorExceptionValues {
   PARAMETER_NULL("parameters.null", "This parameter is null : {0}");

   private String errorCode;
   private String message;

   private AgreementBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
