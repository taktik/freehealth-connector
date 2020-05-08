package org.taktik.connector.technical.ws.impl.strategy;

import org.taktik.connector.technical.exception.RetryNextEndpointException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import org.taktik.connector.technical.ws.impl.AbstractWsSender;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoRetryInvokeStrategy extends AbstractWsSender implements InvokeStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(NoRetryInvokeStrategy.class);

   public boolean invoke(InvokeStrategyContext ctx) {
      try {
         ctx.setResponse(super.call(ctx.getRequest()));
         return true;
      } catch (RetryNextEndpointException var3) {
         if (var3.hasContext()) {
            ctx.setResponse(new GenericResponse(var3.getContext().getMessage()));
            return true;
         } else {
            return this.handleException(ctx, var3);
         }
      } catch (TechnicalConnectorException var4) {
         return this.handleException(ctx, var4);
      }
   }

   private boolean handleException(InvokeStrategyContext ctx, Exception e) {
      Throwable reason = ExceptionUtils.getRootCause(e);
      LOG.error("Cannot send SOAP message. Reason [" + reason + "]", e);
      ctx.setException(new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ExceptionUtils.getRootCause(e), new Object[]{ExceptionUtils.getRootCauseMessage(e)}));
      return false;
   }
}
