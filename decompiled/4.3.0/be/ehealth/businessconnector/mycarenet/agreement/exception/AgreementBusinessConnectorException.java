package be.ehealth.businessconnector.mycarenet.agreement.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import java.text.MessageFormat;

public class AgreementBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 1L;

   public AgreementBusinessConnectorException(AgreementBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }
}
