package sun.security.smartcardio;

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.AccessController;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import sun.security.action.GetPropertyAction;

public final class ChannelImpl extends CardChannel {
   private final CardImpl card;
   private final int channel;
   private volatile boolean isClosed;
   private static final boolean t0GetResponse = getBooleanProperty("sun.security.smartcardio.t0GetResponse", true);
   private static final boolean t1GetResponse = getBooleanProperty("sun.security.smartcardio.t1GetResponse", true);
   private static final boolean t1StripLe = getBooleanProperty("sun.security.smartcardio.t1StripLe", false);
   private static final byte[] B0 = new byte[0];

   ChannelImpl(CardImpl card, int channel) {
      this.card = card;
      this.channel = channel;
   }

   void checkClosed() {
      this.card.checkState();
      if (this.isClosed) {
         throw new IllegalStateException("Logical channel has been closed");
      }
   }

   public Card getCard() {
      return this.card;
   }

   public int getChannelNumber() {
      this.checkClosed();
      return this.channel;
   }

   private static void checkManageChannel(byte[] b) {
      if (b.length < 4) {
         throw new IllegalArgumentException("Command APDU must be at least 4 bytes long");
      } else if (b[0] >= 0 && b[1] == 112) {
         throw new IllegalArgumentException("Manage channel command not allowed, use openLogicalChannel()");
      }
   }

   public ResponseAPDU transmit(CommandAPDU command) throws CardException {
      this.checkClosed();
      this.card.checkExclusive();
      byte[] commandBytes = command.getBytes();
      byte[] responseBytes = this.doTransmit(commandBytes);
      return new ResponseAPDU(responseBytes);
   }

   public int transmit(ByteBuffer command, ByteBuffer response) throws CardException {
      this.checkClosed();
      this.card.checkExclusive();
      if (command != null && response != null) {
         if (response.isReadOnly()) {
            throw new ReadOnlyBufferException();
         } else if (command == response) {
            throw new IllegalArgumentException("command and response must not be the same object");
         } else if (response.remaining() < 258) {
            throw new IllegalArgumentException("Insufficient space in response buffer");
         } else {
            byte[] commandBytes = new byte[command.remaining()];
            command.get(commandBytes);
            byte[] responseBytes = this.doTransmit(commandBytes);
            response.put(responseBytes);
            return responseBytes.length;
         }
      } else {
         throw new NullPointerException();
      }
   }

   private static boolean getBooleanProperty(String name, boolean def) {
      String val = (String)AccessController.doPrivileged(new GetPropertyAction(name));
      if (val == null) {
         return def;
      } else if (val.equalsIgnoreCase("true")) {
         return true;
      } else if (val.equalsIgnoreCase("false")) {
         return false;
      } else {
         throw new IllegalArgumentException(name + " must be either 'true' or 'false'");
      }
   }

   private byte[] concat(byte[] b1, byte[] b2, int n2) {
      int n1 = b1.length;
      if (n1 == 0 && n2 == b2.length) {
         return b2;
      } else {
         byte[] res = new byte[n1 + n2];
         System.arraycopy(b1, 0, res, 0, n1);
         System.arraycopy(b2, 0, res, n1, n2);
         return res;
      }
   }

   private byte[] doTransmit(byte[] command) throws CardException {
      try {
         checkManageChannel(command);
         this.setChannel(command);
         int n = command.length;
         boolean t0 = this.card.protocol == 1;
         boolean t1 = this.card.protocol == 2;
         if (t0 && n >= 7 && command[4] == 0) {
            throw new CardException("Extended length forms not supported for T=0");
         } else {
            if ((t0 || t1 && t1StripLe) && n >= 7) {
               int lc = command[4] & 255;
               if (lc != 0) {
                  if (n == lc + 6) {
                     --n;
                  }
               } else {
                  lc = (command[5] & 255) << 8 | command[6] & 255;
                  if (n == lc + 9) {
                     n -= 2;
                  }
               }
            }

            boolean getresponse = t0 && t0GetResponse || t1 && t1GetResponse;
            int k = 0;
            byte[] result = B0;

            byte[] response;
            int rn;
            while(true) {
               ++k;
               if (k >= 32) {
                  throw new CardException("Could not obtain response");
               }

               response = PCSC.SCardTransmit(this.card.cardId, this.card.protocol, command, 0, n);
               rn = response.length;
               if (!getresponse || rn < 2) {
                  break;
               }

               if (rn == 2 && response[0] == 108) {
                  command[n - 1] = response[1];
               } else {
                  if (response[rn - 2] != 97) {
                     break;
                  }

                  if (rn > 2) {
                     result = this.concat(result, response, rn - 2);
                  }

                  command[1] = -64;
                  command[2] = 0;
                  command[3] = 0;
                  command[4] = response[rn - 1];
                  n = 5;
               }
            }

            result = this.concat(result, response, rn);
            return result;
         }
      } catch (PCSCException var10) {
         this.card.handleError(var10);
         throw new CardException(var10);
      }
   }

   private static int getSW(byte[] res) throws CardException {
      if (res.length < 2) {
         throw new CardException("Invalid response length: " + res.length);
      } else {
         int sw1 = res[res.length - 2] & 255;
         int sw2 = res[res.length - 1] & 255;
         return sw1 << 8 | sw2;
      }
   }

   private static boolean isOK(byte[] res) throws CardException {
      return res.length == 2 && getSW(res) == 36864;
   }

   private void setChannel(byte[] com) {
      int cla = com[0];
      if (cla >= 0) {
         if ((cla & 224) != 32) {
            if (this.channel <= 3) {
               com[0] = (byte)(com[0] & 188);
               com[0] = (byte)(com[0] | this.channel);
            } else {
               if (this.channel > 19) {
                  throw new RuntimeException("Unsupported channel number: " + this.channel);
               }

               com[0] = (byte)(com[0] & 176);
               com[0] = (byte)(com[0] | 64);
               com[0] = (byte)(com[0] | this.channel - 4);
            }

         }
      }
   }

   public void close() throws CardException {
      if (this.getChannelNumber() == 0) {
         throw new IllegalStateException("Cannot close basic logical channel");
      } else if (!this.isClosed) {
         this.card.checkExclusive();

         try {
            byte[] com = new byte[]{0, 112, -128, (byte)this.getChannelNumber()};
            this.setChannel(com);
            byte[] res = PCSC.SCardTransmit(this.card.cardId, this.card.protocol, com, 0, com.length);
            if (!isOK(res)) {
               throw new CardException("close() failed: " + PCSC.toString(res));
            }
         } catch (PCSCException var6) {
            this.card.handleError(var6);
            throw new CardException("Could not close channel", var6);
         } finally {
            this.isClosed = true;
         }

      }
   }

   public String toString() {
      return "PC/SC channel " + this.channel;
   }
}
