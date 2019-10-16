package be.ehealth.technicalconnector.ws.impl.strategy;

import be.ehealth.technicalconnector.exception.NoNextEndpointException;
import be.ehealth.technicalconnector.exception.RetryNextEndpointException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.impl.AbstractWsSender;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryStrategy extends AbstractWsSender implements InvokeStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(RetryStrategy.class);
   private static EndpointDistributor distributor = EndpointDistributor.getInstance();

   public boolean invoke(InvokeStrategyContext invokeStrategyContext) {
      GenericRequest genericRequest = invokeStrategyContext.getRequest();
      RetryStrategy.RetryContext ctx = new RetryStrategy.RetryContext(this.getCurrentEndpoint(genericRequest));
      int alternatives = distributor.getAmountOfAlternatives(ctx.endpoint);

      for(int i = 0; i < alternatives; ++i) {
         String activeEndpoint = distributor.getActiveEndpoint(ctx.endpoint);
         if (!ctx.invokedEndpoints.contains(activeEndpoint)) {
            ctx.invokedEndpoints.add(activeEndpoint);
            genericRequest.setEndpoint(activeEndpoint);

            try {
               invokeStrategyContext.setResponse(super.call(genericRequest));
               this.activatePolling(ctx);
               return false;
            } catch (RetryNextEndpointException var8) {
               this.retryNext(ctx, activeEndpoint, var8);
            } catch (TechnicalConnectorException var9) {
               invokeStrategyContext.setException(var9);
               return true;
            }
         } else {
            LOG.debug("Endpoint [{}] already invoked, skipping it.", activeEndpoint);
         }
      }

      if (EndpointDistributor.update()) {
         return this.invoke(invokeStrategyContext);
      } else {
         invokeStrategyContext.setException(new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ExceptionUtils.getRootCause(ctx.lastException), new Object[]{ExceptionUtils.getRootCauseMessage(ctx.lastException)}));
         return true;
      }
   }

   private void activatePolling(RetryStrategy.RetryContext ctx) {
      if (ctx.alternativeActivated) {
         LOG.debug("Activating status page polling!");
         distributor.activatePolling();
      }

   }

   private void retryNext(RetryStrategy.RetryContext ctx, String activeEndpoint, RetryNextEndpointException e) {
      LOG.error("Unable to invoke endpoint [{}], activating next one.", activeEndpoint, e);

      try {
         distributor.activateNextEndPoint(activeEndpoint);
         ctx.alternativeActivated = true;
      } catch (NoNextEndpointException var5) {
         LOG.error("Unable to activate alternative", var5);
      }

      ctx.lastException = e;
   }

   private static class RetryContext {
      String endpoint;
      Exception lastException;
      boolean alternativeActivated;
      List<String> invokedEndpoints = new ArrayList();

      RetryContext(String endpoint) {
         this.endpoint = endpoint;
      }
   }
}
