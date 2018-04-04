package be.ehealth.businessconnector.hub.exception;

public class FolderDecryptionRuntimeException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public FolderDecryptionRuntimeException(String message, Throwable cause) {
      super(message, cause);
   }
}
