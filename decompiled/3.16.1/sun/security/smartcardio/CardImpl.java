package sun.security.smartcardio;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardPermission;

final class CardImpl extends Card {
   private final TerminalImpl terminal;
   final long cardId;
   private final ATR atr;
   final int protocol;
   private final ChannelImpl basicChannel;
   private volatile CardImpl.State state;
   private volatile Thread exclusiveThread;
   private static byte[] commandOpenChannel = new byte[]{0, 112, 0, 0, 1};

   CardImpl(TerminalImpl terminal, String protocol) throws PCSCException {
      this.terminal = terminal;
      int sharingMode = 2;
      byte connectProtocol;
      if (protocol.equals("*")) {
         connectProtocol = 3;
      } else if (protocol.equalsIgnoreCase("T=0")) {
         connectProtocol = 1;
      } else if (protocol.equalsIgnoreCase("T=1")) {
         connectProtocol = 2;
      } else {
         if (!protocol.equalsIgnoreCase("direct")) {
            throw new IllegalArgumentException("Unsupported protocol " + protocol);
         }

         connectProtocol = 0;
         sharingMode = 3;
      }

      this.cardId = PCSC.SCardConnect(terminal.contextId, terminal.name, sharingMode, connectProtocol);
      byte[] status = new byte[2];
      byte[] atrBytes = PCSC.SCardStatus(this.cardId, status);
      this.atr = new ATR(atrBytes);
      this.protocol = status[1] & 255;
      this.basicChannel = new ChannelImpl(this, 0);
      this.state = CardImpl.State.OK;
   }

   void checkState() {
      CardImpl.State s = this.state;
      if (s == CardImpl.State.DISCONNECTED) {
         throw new IllegalStateException("Card has been disconnected");
      } else if (s == CardImpl.State.REMOVED) {
         throw new IllegalStateException("Card has been removed");
      }
   }

   boolean isValid() {
      if (this.state != CardImpl.State.OK) {
         return false;
      } else {
         try {
            PCSC.SCardStatus(this.cardId, new byte[2]);
            return true;
         } catch (PCSCException var2) {
            this.state = CardImpl.State.REMOVED;
            return false;
         }
      }
   }

   private void checkSecurity(String action) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
         sm.checkPermission(new CardPermission(this.terminal.name, action));
      }

   }

   void handleError(PCSCException e) {
      if (e.code == -2146434967) {
         this.state = CardImpl.State.REMOVED;
      }

   }

   public ATR getATR() {
      return this.atr;
   }

   public String getProtocol() {
      switch(this.protocol) {
      case 1:
         return "T=0";
      case 2:
         return "T=1";
      default:
         return "Unknown protocol " + this.protocol;
      }
   }

   public CardChannel getBasicChannel() {
      this.checkSecurity("getBasicChannel");
      this.checkState();
      return this.basicChannel;
   }

   private static int getSW(byte[] b) {
      if (b.length < 2) {
         return -1;
      } else {
         int sw1 = b[b.length - 2] & 255;
         int sw2 = b[b.length - 1] & 255;
         return sw1 << 8 | sw2;
      }
   }

   public CardChannel openLogicalChannel() throws CardException {
      this.checkSecurity("openLogicalChannel");
      this.checkState();
      this.checkExclusive();

      try {
         byte[] response = PCSC.SCardTransmit(this.cardId, this.protocol, commandOpenChannel, 0, commandOpenChannel.length);
         if (response.length == 3 && getSW(response) == 36864) {
            return new ChannelImpl(this, response[0]);
         } else {
            throw new CardException("openLogicalChannel() failed, card response: " + PCSC.toString(response));
         }
      } catch (PCSCException var2) {
         this.handleError(var2);
         throw new CardException("openLogicalChannel() failed", var2);
      }
   }

   void checkExclusive() throws CardException {
      Thread t = this.exclusiveThread;
      if (t != null) {
         if (t != Thread.currentThread()) {
            throw new CardException("Exclusive access established by another Thread");
         }
      }
   }

   public synchronized void beginExclusive() throws CardException {
      this.checkSecurity("exclusive");
      this.checkState();
      if (this.exclusiveThread != null) {
         throw new CardException("Exclusive access has already been assigned to Thread " + this.exclusiveThread.getName());
      } else {
         try {
            PCSC.SCardBeginTransaction(this.cardId);
         } catch (PCSCException var2) {
            this.handleError(var2);
            throw new CardException("beginExclusive() failed", var2);
         }

         this.exclusiveThread = Thread.currentThread();
      }
   }

   public synchronized void endExclusive() throws CardException {
      this.checkState();
      if (this.exclusiveThread != Thread.currentThread()) {
         throw new IllegalStateException("Exclusive access not assigned to current Thread");
      } else {
         try {
            PCSC.SCardEndTransaction(this.cardId, 0);
         } catch (PCSCException var5) {
            this.handleError(var5);
            throw new CardException("beginExclusive() failed", var5);
         } finally {
            this.exclusiveThread = null;
         }

      }
   }

   public byte[] transmitControlCommand(int controlCode, byte[] command) throws CardException {
      this.checkSecurity("transmitControl");
      this.checkState();
      this.checkExclusive();
      if (command == null) {
         throw new NullPointerException();
      } else {
         try {
            byte[] r = PCSC.SCardControl(this.cardId, controlCode, command);
            return r;
         } catch (PCSCException var4) {
            this.handleError(var4);
            throw new CardException("transmitControlCommand() failed : " + var4.getMessage(), var4);
         }
      }
   }

   public void disconnect(boolean reset) throws CardException {
      if (reset) {
         this.checkSecurity("reset");
      }

      if (this.state == CardImpl.State.OK) {
         this.checkExclusive();

         try {
            PCSC.SCardDisconnect(this.cardId, reset ? 0 : 1);
         } catch (PCSCException var6) {
            throw new CardException("disconnect() failed", var6);
         } finally {
            this.state = CardImpl.State.DISCONNECTED;
            this.exclusiveThread = null;
         }

      }
   }

   public String toString() {
      return "PC/SC card in " + this.terminal.getName() + ", protocol " + this.getProtocol() + ", state " + this.state;
   }

   protected void finalize() throws Throwable {
      try {
         if (this.state == CardImpl.State.OK) {
            PCSC.SCardDisconnect(this.cardId, 0);
         }
      } finally {
         super.finalize();
      }

   }

   private static enum State {
      OK,
      REMOVED,
      DISCONNECTED;
   }
}
