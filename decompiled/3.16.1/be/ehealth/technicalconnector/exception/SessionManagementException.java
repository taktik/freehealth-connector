package be.ehealth.technicalconnector.exception;

import java.text.MessageFormat;

public class SessionManagementException extends TechnicalConnectorException {
   private static final long serialVersionUID = 447147416920783199L;

   public SessionManagementException(SessionManagementExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }

   public SessionManagementException(SessionManagementExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }
}
