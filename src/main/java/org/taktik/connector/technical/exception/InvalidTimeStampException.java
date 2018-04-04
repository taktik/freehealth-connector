package org.taktik.connector.technical.exception;

public class InvalidTimeStampException extends TechnicalConnectorException {
   private static final long serialVersionUID = -652848945589590947L;

   public InvalidTimeStampException(String errorMessage, Exception e) {
      super(TechnicalConnectorExceptionValues.INVALID_TIMESTAMP_TOKEN, errorMessage, e);
   }

   public InvalidTimeStampException(String errorMessage) {
      super(TechnicalConnectorExceptionValues.INVALID_TIMESTAMP_TOKEN, errorMessage);
   }
}
