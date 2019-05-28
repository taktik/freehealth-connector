package be.ehealth.technicalconnector.exception;

public class SilentInstantiationException extends TechnicalConnectorException {
   private static final long serialVersionUID = -108607107750227196L;

   public SilentInstantiationException(Exception e) {
      super((TechnicalConnectorExceptionValues)TechnicalConnectorExceptionValues.ERROR_TECHNICAL, (Throwable)e, (Object[])(e.getMessage()));
   }
}
