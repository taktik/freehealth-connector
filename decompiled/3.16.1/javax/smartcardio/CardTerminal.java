package javax.smartcardio;

public abstract class CardTerminal {
   public abstract String getName();

   public abstract Card connect(String var1) throws CardException;

   public abstract boolean isCardPresent() throws CardException;

   public abstract boolean waitForCardPresent(long var1) throws CardException;

   public abstract boolean waitForCardAbsent(long var1) throws CardException;
}
