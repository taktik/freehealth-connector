package org.taktik.connector.technical.ws.impl.strategy;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;

public interface InvokeStrategy {
   GenericResponse invoke(GenericRequest var1) throws TechnicalConnectorException;
}
