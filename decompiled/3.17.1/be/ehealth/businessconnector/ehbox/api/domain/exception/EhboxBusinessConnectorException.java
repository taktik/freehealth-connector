package be.ehealth.businessconnector.ehbox.api.domain.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import java.text.MessageFormat;

public class EhboxBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 236496056490741436L;

   public EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }

   public EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }
}
