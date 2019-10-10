package be.fgov.ehealth.technicalconnector.ra.exceptions;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.technicalconnector.ra.utils.RaUtils;
import java.util.List;

public class RaException extends TechnicalConnectorException {
   private static final long serialVersionUID = 1L;
   private final StatusResponseType statusResponseType;

   public RaException(String msg, Throwable cause, StatusResponseType statusResponseType) {
      super(TechnicalConnectorExceptionValues.ERROR_WS, cause, msg);
      this.statusResponseType = statusResponseType;
   }

   public StatusResponseType getStatusResponse() {
      return this.statusResponseType;
   }

   public List<String> getErrorCodes() {
      return RaUtils.getErrorCodes(this.getStatusResponse());
   }
}
