package be.ehealth.technicalconnector.exception;

public class CredentialException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public CredentialException(String message) {
      super(message);
   }

   public CredentialException(Exception e) {
      super(e);
   }
}
