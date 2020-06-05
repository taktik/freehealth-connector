package be.ehealth.businessconnector.mycarenet.memberdata.exception;

import be.ehealth.technicalconnector.exception.ConnectorException;
import java.text.MessageFormat;

public class MemberDataBusinessConnectorException extends ConnectorException {
   private static final long serialVersionUID = 1L;

   public MemberDataBusinessConnectorException(MemberDataBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
   }
}
