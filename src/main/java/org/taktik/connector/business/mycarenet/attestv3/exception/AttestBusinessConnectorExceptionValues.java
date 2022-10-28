package org.taktik.connector.business.mycarenet.attestv3.exception;

public enum AttestBusinessConnectorExceptionValues {
   PARAMETER_NULL("parameters.null", "This parameter is null : {0}");

   private String errorCode;
   private String message;

   private AttestBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
