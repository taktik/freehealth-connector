package javax.smartcardio;

public abstract class TerminalFactorySpi {
   protected TerminalFactorySpi() {
   }

   protected abstract CardTerminals engineTerminals();
}
