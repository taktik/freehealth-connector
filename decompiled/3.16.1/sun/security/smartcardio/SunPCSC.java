package sun.security.smartcardio;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactorySpi;

public final class SunPCSC extends Provider {
   private static final long serialVersionUID = 6168388284028876579L;

   public SunPCSC() {
      super("SunPCSC", 1.6D, "Sun PC/SC provider");
      AccessController.doPrivileged(new PrivilegedAction() {
         public Void run() {
            SunPCSC.this.put("TerminalFactory.PC/SC", SunPCSC.Factory.class.getName());
            return null;
         }
      });
   }

   public static final class Factory extends TerminalFactorySpi {
      public Factory(Object paramObject) throws PCSCException {
         if (paramObject != null) {
            throw new IllegalArgumentException("SunPCSC factory does not use parameters");
         } else {
            PCSC.checkAvailable();
            PCSCTerminals.initContext();
         }
      }

      protected CardTerminals engineTerminals() {
         return new PCSCTerminals();
      }
   }
}
