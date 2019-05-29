package be.ehealth.technicalconnector.exception;

public class RetryNextEndpointException extends TechnicalConnectorException {
   private static final long serialVersionUID = 1L;

   public RetryNextEndpointException(Throwable cause) {
      super("Unable to connect to endpoint, allowing to retry next one.", "bcp-retry_next_endpoint", cause);
   }
}
