package be.ehealth.businessconnector.wsconsent.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import java.text.MessageFormat;

public class WsConsentBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 1L;

   public WsConsentBusinessConnectorException(WsConsentBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }

   public WsConsentBusinessConnectorException(WsConsentBusinessConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }
}
