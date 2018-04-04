package be.fgov.ehealth.technicalconnector.ra.exceptions;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;

public class RaException extends TechnicalConnectorException {
   private static final long serialVersionUID = 1L;

   public RaException(String msg, Throwable cause) {
      super(TechnicalConnectorExceptionValues.ERROR_WS, cause, msg);
   }
}
