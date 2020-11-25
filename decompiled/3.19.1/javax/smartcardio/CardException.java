package javax.smartcardio;

public class CardException extends Exception {
   private static final long serialVersionUID = 7787607144922050628L;

   public CardException(String message) {
      super(message);
   }

   public CardException(Throwable cause) {
      super(cause);
   }

   public CardException(String message, Throwable cause) {
      super(message, cause);
   }
}
