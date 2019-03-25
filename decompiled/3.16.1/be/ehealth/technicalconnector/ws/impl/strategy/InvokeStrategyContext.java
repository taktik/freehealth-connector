package be.ehealth.technicalconnector.ws.impl.strategy;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;

public class InvokeStrategyContext {
   private GenericRequest request;
   private GenericResponse response;
   private TechnicalConnectorException exception;

   public InvokeStrategyContext(GenericRequest request) {
      this.request = request;
   }

   public GenericRequest getRequest() {
      return this.request;
   }

   public GenericResponse getResponse() {
      return this.response;
   }

   public void setResponse(GenericResponse response) {
      this.response = response;
   }

   public TechnicalConnectorException getException() {
      return this.exception;
   }

   public void setException(TechnicalConnectorException exception) {
      this.exception = exception;
   }

   public boolean hasException() {
      return this.exception != null;
   }
}
