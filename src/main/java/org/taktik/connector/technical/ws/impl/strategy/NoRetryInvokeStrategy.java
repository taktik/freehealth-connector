package org.taktik.connector.technical.ws.impl.strategy;

import org.taktik.connector.technical.exception.RetryNextEndpointException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import org.taktik.connector.technical.ws.impl.AbstractWsSender;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoRetryInvokeStrategy extends AbstractWsSender implements InvokeStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(NoRetryInvokeStrategy.class);

   public GenericResponse invoke(GenericRequest genericRequest) throws TechnicalConnectorException {
      try {
         return super.call(genericRequest);
      } catch (RetryNextEndpointException var4) {
         Throwable reason = ExceptionUtils.getRootCause(var4);
         LOG.error("Cannot send SOAP message. Reason [" + reason + "]", var4);
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var4, new Object[]{reason});
      }
   }
}
