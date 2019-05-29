package be.ehealth.businessconnector.ehbox.v3.exception;

import be.ehealth.businessconnector.ehbox.api.domain.Message;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import java.util.ArrayList;
import java.util.List;

public class EhboxCryptoException extends TechnicalConnectorException {
   private static final long serialVersionUID = 1L;
   private Message ehBoxMessage;
   private List<UnsealConnectorException> exceptions;

   public EhboxCryptoException(List<UnsealConnectorException> exceptions, Message ehBoxMessage) {
      super(TechnicalConnectorExceptionValues.ERROR_CRYPTO, "Unable to decrypt ehbox message.");
      this.exceptions = exceptions;
      this.ehBoxMessage = ehBoxMessage;
   }

   public EhboxCryptoException(UnsealConnectorException exception, Message ehBoxMessage) {
      super((TechnicalConnectorExceptionValues)TechnicalConnectorExceptionValues.ERROR_CRYPTO, (Throwable)exception, (Object[])("Unable to decrypt ehbox message."));
      this.exceptions = new ArrayList();
      this.exceptions.add(exception);
      this.ehBoxMessage = ehBoxMessage;
   }

   public Message getEhBoxMessage() {
      return this.ehBoxMessage;
   }

   public List<UnsealConnectorException> getExceptions() {
      return this.exceptions;
   }
}
