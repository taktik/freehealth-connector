package be.ehealth.businessconnector.dmg.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import java.text.MessageFormat;

public class DmgBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = -5527698631504638L;

   public DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }

   public DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues errorCodeValue) {
      super(errorCodeValue.getMessage(), errorCodeValue.getErrorCode());
   }

   public DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues errorCodeValue, Throwable cause, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), cause);
   }
}
