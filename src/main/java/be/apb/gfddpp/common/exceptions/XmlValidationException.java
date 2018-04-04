package be.apb.gfddpp.common.exceptions;


public class XmlValidationException extends Exception {
   private static final long serialVersionUID = 1L;

   public XmlValidationException(String message, Throwable cause) {
      super(message, cause);
   }

   public XmlValidationException(String message) {
      super(message);
   }

   public XmlValidationException(Throwable cause) {
      super(cause);
   }
}
