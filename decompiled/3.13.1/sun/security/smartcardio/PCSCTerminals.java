package sun.security.smartcardio;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;

final class PCSCTerminals extends CardTerminals {
   private static long contextId;
   private Map<String, PCSCTerminals.ReaderState> stateMap;
   private static final Map<String, Reference<TerminalImpl>> terminals = new HashMap();

   static synchronized void initContext() throws PCSCException {
      if (contextId == 0L) {
         contextId = PCSC.SCardEstablishContext(0);
      }

   }

   private static synchronized TerminalImpl implGetTerminal(String name) {
      Reference<TerminalImpl> ref = (Reference)terminals.get(name);
      TerminalImpl terminal = ref != null ? (TerminalImpl)ref.get() : null;
      if (terminal != null) {
         return terminal;
      } else {
         terminal = new TerminalImpl(contextId, name);
         terminals.put(name, new WeakReference(terminal));
         return terminal;
      }
   }

   public synchronized List<CardTerminal> list(CardTerminals.State state) throws CardException {
      if (state == null) {
         throw new NullPointerException();
      } else {
         try {
            String[] readerNames = PCSC.SCardListReaders(contextId);
            List<CardTerminal> list = new ArrayList(readerNames.length);
            if (this.stateMap == null) {
               if (state == CardTerminals.State.CARD_INSERTION) {
                  state = CardTerminals.State.CARD_PRESENT;
               } else if (state == CardTerminals.State.CARD_REMOVAL) {
                  state = CardTerminals.State.CARD_ABSENT;
               }
            }

            String[] arr$ = readerNames;
            int len$ = readerNames.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               String readerName = arr$[i$];
               CardTerminal terminal = implGetTerminal(readerName);
               PCSCTerminals.ReaderState readerState;
               switch(state) {
               case ALL:
                  list.add(terminal);
                  break;
               case CARD_PRESENT:
                  if (terminal.isCardPresent()) {
                     list.add(terminal);
                  }
                  break;
               case CARD_ABSENT:
                  if (!terminal.isCardPresent()) {
                     list.add(terminal);
                  }
                  break;
               case CARD_INSERTION:
                  readerState = (PCSCTerminals.ReaderState)this.stateMap.get(readerName);
                  if (readerState != null && readerState.isInsertion()) {
                     list.add(terminal);
                  }
                  break;
               case CARD_REMOVAL:
                  readerState = (PCSCTerminals.ReaderState)this.stateMap.get(readerName);
                  if (readerState != null && readerState.isRemoval()) {
                     list.add(terminal);
                  }
                  break;
               default:
                  throw new CardException("Unknown state: " + state);
               }
            }

            return Collections.unmodifiableList(list);
         } catch (PCSCException var10) {
            throw new CardException("list() failed", var10);
         }
      }
   }

   public synchronized boolean waitForChange(long timeout) throws CardException {
      if (timeout < 0L) {
         throw new IllegalArgumentException("Timeout must not be negative: " + timeout);
      } else {
         if (this.stateMap == null) {
            this.stateMap = new HashMap();
            this.waitForChange(0L);
         }

         if (timeout == 0L) {
            timeout = -1L;
         }

         try {
            String[] readerNames = PCSC.SCardListReaders(contextId);
            int n = readerNames.length;
            if (n == 0) {
               throw new IllegalStateException("No terminals available");
            } else {
               int[] status = new int[n];
               PCSCTerminals.ReaderState[] readerStates = new PCSCTerminals.ReaderState[n];

               int i;
               for(i = 0; i < readerNames.length; ++i) {
                  String name = readerNames[i];
                  PCSCTerminals.ReaderState state = (PCSCTerminals.ReaderState)this.stateMap.get(name);
                  if (state == null) {
                     state = new PCSCTerminals.ReaderState();
                  }

                  readerStates[i] = state;
                  status[i] = state.get();
               }

               status = PCSC.SCardGetStatusChange(contextId, timeout, status, readerNames);
               this.stateMap.clear();

               for(i = 0; i < n; ++i) {
                  PCSCTerminals.ReaderState state = readerStates[i];
                  state.update(status[i]);
                  this.stateMap.put(readerNames[i], state);
               }

               return true;
            }
         } catch (PCSCException var10) {
            if (var10.code == -2146435062) {
               return false;
            } else {
               throw new CardException("waitForChange() failed", var10);
            }
         }
      }
   }

   static List<CardTerminal> waitForCards(List<? extends CardTerminal> terminals, long timeout, boolean wantPresent) throws CardException {
      long thisTimeout;
      if (timeout == 0L) {
         timeout = -1L;
         thisTimeout = -1L;
      } else {
         thisTimeout = 0L;
      }

      String[] names = new String[terminals.size()];
      int i = 0;

      TerminalImpl impl;
      for(Iterator i$ = terminals.iterator(); i$.hasNext(); names[i++] = impl.name) {
         CardTerminal terminal = (CardTerminal)i$.next();
         if (!(terminal instanceof TerminalImpl)) {
            throw new IllegalArgumentException("Invalid terminal type: " + terminal.getClass().getName());
         }

         impl = (TerminalImpl)terminal;
      }

      int[] status = new int[names.length];
      Arrays.fill(status, 0);

      try {
         ArrayList results;
         do {
            status = PCSC.SCardGetStatusChange(contextId, thisTimeout, status, names);
            thisTimeout = timeout;
            results = null;

            for(i = 0; i < names.length; ++i) {
               boolean nowPresent = (status[i] & 32) != 0;
               if (nowPresent == wantPresent) {
                  if (results == null) {
                     results = new ArrayList();
                  }

                  results.add(implGetTerminal(names[i]));
               }
            }
         } while(results == null);

         return Collections.unmodifiableList(results);
      } catch (PCSCException var11) {
         if (var11.code == -2146435062) {
            return Collections.emptyList();
         } else {
            throw new CardException("waitForCard() failed", var11);
         }
      }
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$javax$smartcardio$CardTerminals$State = new int[CardTerminals.State.values().length];

      static {
         try {
            $SwitchMap$javax$smartcardio$CardTerminals$State[CardTerminals.State.ALL.ordinal()] = 1;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$javax$smartcardio$CardTerminals$State[CardTerminals.State.CARD_PRESENT.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$javax$smartcardio$CardTerminals$State[CardTerminals.State.CARD_ABSENT.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$javax$smartcardio$CardTerminals$State[CardTerminals.State.CARD_INSERTION.ordinal()] = 4;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$javax$smartcardio$CardTerminals$State[CardTerminals.State.CARD_REMOVAL.ordinal()] = 5;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }

   private static class ReaderState {
      private int current = 0;
      private int previous = 0;

      int get() {
         return this.current;
      }

      void update(int newState) {
         this.previous = this.current;
         this.current = newState;
      }

      boolean isInsertion() {
         return !present(this.previous) && present(this.current);
      }

      boolean isRemoval() {
         return present(this.previous) && !present(this.current);
      }

      static boolean present(int state) {
         return (state & 32) != 0;
      }
   }
}
