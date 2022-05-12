package be.ehealth.technicalconnector.ws.impl.strategy;

import be.ehealth.technicalconnector.exception.NoNextEndpointException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.ehealth.technicalconnector.ws.impl.GenericWsSenderImpl;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor;
import java.util.ArrayList;
import java.util.List;
import javax.xml.soap.SOAPMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryStrategy extends GenericWsSenderImpl implements InvokeStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(RetryStrategy.class);
   private static EndpointDistributor distributor = EndpointDistributor.getInstance();

   public RetryStrategy() {
   }

   public boolean invoke(InvokeStrategyContext invokeStrategyContext) {
      RetryStrategy.RetryNotifier.reset();
      GenericRequest genericRequest = invokeStrategyContext.getRequest();
      RetryContext ctx = new RetryContext(this.getCurrentEndpoint(genericRequest));
      int alternatives = distributor.getAmountOfAlternatives(ctx.endpoint);

      for(int i = 0; i < alternatives; ++i) {
         String activeEndpoint = distributor.getActiveEndpoint(ctx.endpoint);
         if (!ctx.invokedEndpoints.contains(activeEndpoint)) {
            ctx.invokedEndpoints.add(activeEndpoint);
            genericRequest.setEndpoint(activeEndpoint);

            try {
               invokeStrategyContext.setResponse(super.call(genericRequest));
               if (!RetryStrategy.RetryNotifier.activated()) {
                  invokeStrategyContext.setException((TechnicalConnectorException)null);
                  return true;
               }
            } catch (TechnicalConnectorException var8) {
               invokeStrategyContext.setException(var8);
            }

            if (RetryStrategy.RetryNotifier.activated()) {
               this.retryNext(ctx, activeEndpoint, RetryStrategy.RetryNotifier.getException());
               this.activatePolling(ctx);
               RetryStrategy.RetryNotifier.reset();
            }
         } else {
            LOG.debug("Endpoint [{}] already invoked, skipping it.", activeEndpoint);
         }
      }

      LOG.debug("All alternatives are used. Returning last exception");
      if (RetryStrategy.RetryNotifier.activated()) {
         RetryStrategy.RetryNotifier.reset();
      }

      if (ctx.lastException instanceof MessageLevelRetryException) {
         MessageLevelRetryException exception = (MessageLevelRetryException)ctx.lastException;
         invokeStrategyContext.setResponse(exception.getResponse());
      } else {
         invokeStrategyContext.setException(new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ExceptionUtils.getRootCause(ctx.lastException), new Object[]{ExceptionUtils.getRootCauseMessage(ctx.lastException)}));
      }

      return true;
   }

   private void activatePolling(RetryContext ctx) {
      if (ctx.alternativeActivated) {
         LOG.debug("Activating status page polling!");
         distributor.activatePolling();
      }

   }

   private void retryNext(RetryContext ctx, String activeEndpoint, Exception e) {
      LOG.error("Unable to invoke endpoint [{}], activating next one. Reason: {}", activeEndpoint, ExceptionUtils.getRootCauseMessage(e));

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

   public static class RetryNotifier {
      private static final ThreadLocal<Exception> HOLDER = new ThreadLocal();

      public RetryNotifier() {
      }

      public static void activate(Exception e) {
         HOLDER.set(e);
      }

      public static void activate(SOAPMessage message) {
         HOLDER.set(new MessageLevelRetryException(message));
      }

      public static void reset() {
         HOLDER.remove();
      }

      public static boolean activated() {
         return HOLDER.get() != null;
      }

      public static Exception getException() {
         return (Exception)HOLDER.get();
      }
   }

   private static class MessageLevelRetryException extends Exception {
      private SOAPMessage message;

      public MessageLevelRetryException(SOAPMessage message) {
         this.message = message;
      }

      public GenericResponse getResponse() {
         return new GenericResponse(this.message);
      }
   }
}
