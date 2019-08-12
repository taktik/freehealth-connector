package be.ehealth.businessconnector.therlink.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import java.text.MessageFormat;

public class TherLinkBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 1L;

   public TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }

   public TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }
}
