package javax.smartcardio;

import java.util.Iterator;
import java.util.List;

public abstract class CardTerminals {
   protected CardTerminals() {
   }

   public List<CardTerminal> list() throws CardException {
      return this.list(CardTerminals.State.ALL);
   }

   public abstract List<CardTerminal> list(CardTerminals.State var1) throws CardException;

   public CardTerminal getTerminal(String name) {
      if (name == null) {
         throw new NullPointerException();
      } else {
         try {
            Iterator i$ = this.list().iterator();

            CardTerminal terminal;
            do {
               if (!i$.hasNext()) {
                  return null;
               }

               terminal = (CardTerminal)i$.next();
            } while(!terminal.getName().equals(name));

            return terminal;
         } catch (CardException var4) {
            return null;
         }
      }
   }

   public void waitForChange() throws CardException {
      this.waitForChange(0L);
   }

   public abstract boolean waitForChange(long var1) throws CardException;

   public static enum State {
      ALL,
      CARD_PRESENT,
      CARD_ABSENT,
      CARD_INSERTION,
      CARD_REMOVAL;
   }
}
