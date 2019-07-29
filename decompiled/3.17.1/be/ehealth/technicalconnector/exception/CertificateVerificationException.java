package be.ehealth.technicalconnector.exception;

public class CertificateVerificationException extends TechnicalConnectorException {
   private static final long serialVersionUID = 1L;

   public CertificateVerificationException(String message, Throwable cause) {
      super(TechnicalConnectorExceptionValues.ERROR_CERTIFICATE_VALIDATION, cause, message);
   }

   public CertificateVerificationException(String message) {
      super(TechnicalConnectorExceptionValues.ERROR_CERTIFICATE_VALIDATION, message);
   }
}
