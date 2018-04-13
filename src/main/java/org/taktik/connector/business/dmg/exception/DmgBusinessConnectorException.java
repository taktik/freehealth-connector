package org.taktik.connector.business.dmg.exception;

import org.taktik.connector.technical.exception.ConnectorException;
import java.text.MessageFormat;

public class DmgBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = -5527698631504638L;

   public DmgBusinessConnectorException(DmgBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }
}
