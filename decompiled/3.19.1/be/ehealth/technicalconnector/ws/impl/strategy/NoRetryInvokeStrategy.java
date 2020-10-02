package be.ehealth.technicalconnector.ws.impl.strategy;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.ws.impl.AbstractWsSender;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoRetryInvokeStrategy extends AbstractWsSender implements InvokeStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(NoRetryInvokeStrategy.class);

   public boolean invoke(InvokeStrategyContext ctx) {
      try {
         ctx.setResponse(super.call(ctx.getRequest()));
         return true;
      } catch (TechnicalConnectorException var3) {
         return this.handleException(ctx, var3);
      }
   }

   private boolean handleException(InvokeStrategyContext ctx, Exception e) {
      Throwable reason = ExceptionUtils.getRootCause(e);
      LOG.error("Cannot send SOAP message. Reason [" + reason + "]", e);
      ctx.setException(new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ExceptionUtils.getRootCause(e), new Object[]{ExceptionUtils.getRootCauseMessage(e)}));
      return false;
   }
}
