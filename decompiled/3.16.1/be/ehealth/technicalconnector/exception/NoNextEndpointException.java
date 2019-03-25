package be.ehealth.technicalconnector.exception;

public class NoNextEndpointException extends TechnicalConnectorException {
   private static final long serialVersionUID = 1L;

   public NoNextEndpointException(String msg) {
      super(msg, "bcp-no_next_endpoint");
   }
}
