package be.ehealth.technicalconnector.ws.impl.strategy;

import be.ehealth.technicalconnector.exception.RetryNextEndpointException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.ehealth.technicalconnector.ws.impl.AbstractWsSender;
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
