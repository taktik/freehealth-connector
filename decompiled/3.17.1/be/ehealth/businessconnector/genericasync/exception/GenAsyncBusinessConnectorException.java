package be.ehealth.businessconnector.genericasync.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import java.text.MessageFormat;

public class GenAsyncBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = -5527698631504638L;

   public GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }

   public GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues errorCodeValue) {
      super(errorCodeValue.getMessage(), errorCodeValue.getErrorCode());
   }

   public GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }
}
