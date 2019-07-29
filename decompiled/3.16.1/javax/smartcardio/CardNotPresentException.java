package javax.smartcardio;

public class CardNotPresentException extends CardException {
   private static final long serialVersionUID = 1346879911706545215L;

   public CardNotPresentException(String message) {
      super(message);
   }

   public CardNotPresentException(Throwable cause) {
      super(cause);
   }

   public CardNotPresentException(String message, Throwable cause) {
      super(message, cause);
   }
}
