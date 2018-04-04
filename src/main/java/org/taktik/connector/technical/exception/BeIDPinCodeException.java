package org.taktik.connector.technical.exception;

public class BeIDPinCodeException extends TechnicalConnectorException {
   private static final long serialVersionUID = -652848945589590947L;

   public BeIDPinCodeException(Exception e) {
      super((TechnicalConnectorExceptionValues)TechnicalConnectorExceptionValues.ERROR_EID_LOGIN, (Throwable)e, e.getMessage());
   }
}
