package be.ehealth.businessconnector.chapterIV.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.fgov.ehealth.errors.soa.v1.SOAErrorType;
import java.text.MessageFormat;

public class ChapterIVBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 8040708257962683258L;
   private SOAErrorType cause;

   public ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues errorCodeValue, SOAErrorType causeError, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
      this.cause = causeError;
   }

   public ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues errorCodeValue, Throwable e, SOAErrorType causeError, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
      this.cause = causeError;
   }

   public ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues errorCodeValue, SOAErrorType causeError) {
      super(errorCodeValue.getMessage(), errorCodeValue.getErrorCode());
      this.cause = causeError;
   }

   public ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }

   public ChapterIVBusinessConnectorException(ChapterIVBusinessConnectorExceptionValues error, String param) {
      this(error, (SOAErrorType)null, param);
   }

   public SOAErrorType getSOAError() {
      return this.cause;
   }
}
