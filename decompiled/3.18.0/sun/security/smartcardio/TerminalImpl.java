package sun.security.smartcardio;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardNotPresentException;
import javax.smartcardio.CardTerminal;

final class TerminalImpl extends CardTerminal {
   final long contextId;
   final String name;
   private CardImpl card;

   TerminalImpl(long contextId, String name) {
      this.contextId = contextId;
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public synchronized Card connect(String protocol) throws CardException {
      if (this.card != null) {
         if (this.card.isValid()) {
            String cardProto = this.card.getProtocol();
            if (!protocol.equals("*") && !protocol.equalsIgnoreCase(cardProto)) {
               throw new CardException("Cannot connect using " + protocol + ", connection already established using " + cardProto);
            }

            return this.card;
         }

         this.card = null;
      }

      try {
         this.card = new CardImpl(this, protocol);
         return this.card;
      } catch (PCSCException var3) {
         if (var3.code == -2146434967) {
            throw new CardNotPresentException("No card present", var3);
         } else {
            throw new CardException("connect() failed", var3);
         }
      }
   }

   public boolean isCardPresent() throws CardException {
      try {
         int[] status = PCSC.SCardGetStatusChange(this.contextId, 0L, new int[]{0}, new String[]{this.name});
         return (status[0] & 32) != 0;
      } catch (PCSCException var2) {
         throw new CardException("isCardPresent() failed", var2);
      }
   }

   private boolean waitForCard(boolean wantPresent, long timeout) throws CardException {
      if (timeout < 0L) {
         throw new IllegalArgumentException("timeout must not be negative");
      } else {
         if (timeout == 0L) {
            timeout = -1L;
         }

         int[] status = new int[]{0};
         String[] readers = new String[]{this.name};

         try {
            status = PCSC.SCardGetStatusChange(this.contextId, 0L, status, readers);
            boolean present = (status[0] & 32) != 0;
            if (wantPresent == present) {
               return true;
            } else {
               status = PCSC.SCardGetStatusChange(this.contextId, timeout, status, readers);
               present = (status[0] & 32) != 0;
               if (wantPresent != present) {
                  throw new CardException("wait mismatch");
               } else {
                  return true;
               }
            }
         } catch (PCSCException var7) {
            if (var7.code == -2146435062) {
               return false;
            } else {
               throw new CardException("waitForCard() failed", var7);
            }
         }
      }
   }

   public boolean waitForCardPresent(long timeout) throws CardException {
      return this.waitForCard(true, timeout);
   }

   public boolean waitForCardAbsent(long timeout) throws CardException {
      return this.waitForCard(false, timeout);
   }

   public String toString() {
      return "PC/SC terminal " + this.name;
   }
}
