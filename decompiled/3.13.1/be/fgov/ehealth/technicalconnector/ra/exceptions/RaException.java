package be.fgov.ehealth.technicalconnector.ra.exceptions;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;

public class RaException extends TechnicalConnectorException {
   private static final long serialVersionUID = 1L;

   public RaException(String msg, Throwable cause) {
      super(TechnicalConnectorExceptionValues.ERROR_WS, cause, msg);
   }
}
