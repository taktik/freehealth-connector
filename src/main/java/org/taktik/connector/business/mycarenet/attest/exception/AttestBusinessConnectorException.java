package org.taktik.connector.business.mycarenet.attest.exception;

import org.taktik.connector.business.mycarenet.attestv2.exception.AttestBusinessConnectorExceptionValues;
import org.taktik.connector.technical.exception.ConnectorException;
import java.text.MessageFormat;

public class AttestBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 1L;

   public AttestBusinessConnectorException(AttestBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }

   public AttestBusinessConnectorException(AttestBusinessConnectorExceptionValues errorCodeValue) {
      super(errorCodeValue.getMessage(), errorCodeValue.getErrorCode());
   }

   public AttestBusinessConnectorException(AttestBusinessConnectorExceptionValues errorCodeValue, Throwable cause, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), cause);
   }
}
