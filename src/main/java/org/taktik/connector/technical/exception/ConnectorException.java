package org.taktik.connector.technical.exception;

public class ConnectorException extends Exception {
   private static final long serialVersionUID = 5929837295133453608L;
   private String errorCode = "";

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
