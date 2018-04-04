package be.ehealth.businessconnector.genins.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.fgov.ehealth.errors.soa.v1.SOAErrorType;
import java.text.MessageFormat;

public class GenInsBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 8040708257962683258L;
   private SOAErrorType causeSoaErrorType;

   public GenInsBusinessConnectorException(GenInsBusinessConnectorExceptionValues errorCodeValue, SOAErrorType causeError, Throwable cause, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), cause);
      this.causeSoaErrorType = causeError;
   }

   public GenInsBusinessConnectorException(GenInsBusinessConnectorExceptionValues errorCodeValue, SOAErrorType causeError, Throwable cause) {
      super(errorCodeValue.getMessage(), errorCodeValue.getErrorCode(), cause);
      this.causeSoaErrorType = causeError;
   }

   public GenInsBusinessConnectorException(GenInsBusinessConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }

   public GenInsBusinessConnectorException(GenInsBusinessConnectorExceptionValues errorCodeValue, Throwable cause, String param) {
      this(errorCodeValue, (SOAErrorType)null, cause, param);
   }

   public SOAErrorType getSOAError() {
      return this.causeSoaErrorType;
   }
}
