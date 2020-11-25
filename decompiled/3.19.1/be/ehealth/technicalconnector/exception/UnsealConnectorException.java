package be.ehealth.technicalconnector.exception;

import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import java.text.MessageFormat;

public class UnsealConnectorException extends TechnicalConnectorException {
   private CryptoResult<?> result;
   private static final long serialVersionUID = -3496880847277240189L;

   public UnsealConnectorException(UnsealConnectorExceptionValues errorCodeValue, CryptoResult<?> result, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
      this.result = result;
   }

   public UnsealConnectorException(UnsealConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
   }

   public final CryptoResult<?> getUnsealResult() {
      return this.result;
   }
}
