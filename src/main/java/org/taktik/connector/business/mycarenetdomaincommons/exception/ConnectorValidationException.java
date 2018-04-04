package org.taktik.connector.business.mycarenetdomaincommons.exception;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import java.util.List;

public final class ConnectorValidationException extends TechnicalConnectorException {
   private List<ValidationError> validationErrors;
   private static final long serialVersionUID = 2463769008336629091L;

   public ConnectorValidationException(List<ValidationError> validationErrors) {
      super(TechnicalConnectorExceptionValues.INVALID_MYCARENET_INPUT_OBJECT, validationErrors);
      this.validationErrors = validationErrors;
   }

   public List<ValidationError> getValidationErrors() {
      return this.validationErrors;
   }
}
