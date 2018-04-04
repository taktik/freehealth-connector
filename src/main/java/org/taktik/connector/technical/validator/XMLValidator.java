package org.taktik.connector.technical.validator;

import org.taktik.connector.technical.exception.TechnicalConnectorException;

public interface XMLValidator {
   void validate(Object var1) throws TechnicalConnectorException;
}
