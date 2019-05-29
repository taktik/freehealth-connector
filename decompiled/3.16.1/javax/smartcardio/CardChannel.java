package javax.smartcardio;

import java.nio.ByteBuffer;

public abstract class CardChannel {
   public abstract Card getCard();

   public abstract int getChannelNumber();

   public abstract ResponseAPDU transmit(CommandAPDU var1) throws CardException;

   public abstract int transmit(ByteBuffer var1, ByteBuffer var2) throws CardException;

   public abstract void close() throws CardException;
}
