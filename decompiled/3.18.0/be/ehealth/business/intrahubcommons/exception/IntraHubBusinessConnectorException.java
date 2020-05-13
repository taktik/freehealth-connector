package be.ehealth.business.intrahubcommons.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import java.text.MessageFormat;

public class IntraHubBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 1L;

   IntraHubBusinessConnectorException(IntraHubBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }

   IntraHubBusinessConnectorException(IntraHubBusinessConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }
}
