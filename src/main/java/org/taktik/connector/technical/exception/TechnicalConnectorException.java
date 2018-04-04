package org.taktik.connector.technical.exception;

import java.text.MessageFormat;

public class TechnicalConnectorException extends ConnectorException {
   private static final long serialVersionUID = 236496056490741436L;
   private TechnicalConnectorExceptionValues category;

   public TechnicalConnectorException(TechnicalConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
      this.category = TechnicalConnectorExceptionValues.CORE_TECHNICAL;
      this.category = errorCodeValue;
   }

   public TechnicalConnectorException(TechnicalConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
      this.category = TechnicalConnectorExceptionValues.CORE_TECHNICAL;
      this.category = errorCodeValue;
   }

   protected TechnicalConnectorException(String format, String errorCode) {
      super(format, errorCode);
      this.category = TechnicalConnectorExceptionValues.CORE_TECHNICAL;
   }

   protected TechnicalConnectorException(String format, String errorCode, Throwable e) {
      super(format, errorCode, e);
      this.category = TechnicalConnectorExceptionValues.CORE_TECHNICAL;
   }

   public TechnicalConnectorExceptionValues getCategory() {
      return this.category;
   }
}
