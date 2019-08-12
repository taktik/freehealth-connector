package javax.smartcardio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

public final class ATR implements Serializable {
   private static final long serialVersionUID = 6695383790847736493L;
   private byte[] atr;
   private transient int startHistorical;
   private transient int nHistorical;

   public ATR(byte[] atr) {
      this.atr = (byte[])atr.clone();
      this.parse();
   }

   private void parse() {
      if (this.atr.length >= 2) {
         if (this.atr[0] == 59 || this.atr[0] == 63) {
            int t0 = (this.atr[1] & 240) >> 4;
            int n = this.atr[1] & 15;
            int i = 2;

            while(t0 != 0 && i < this.atr.length) {
               if ((t0 & 1) != 0) {
                  ++i;
               }

               if ((t0 & 2) != 0) {
                  ++i;
               }

               if ((t0 & 4) != 0) {
                  ++i;
               }

               if ((t0 & 8) != 0) {
                  if (i >= this.atr.length) {
                     return;
                  }

                  t0 = (this.atr[i++] & 240) >> 4;
               } else {
                  t0 = 0;
               }
            }

            int k = i + n;
            if (k == this.atr.length || k == this.atr.length - 1) {
               this.startHistorical = i;
               this.nHistorical = n;
            }

         }
      }
   }

   public byte[] getBytes() {
      return (byte[])this.atr.clone();
   }

   public byte[] getHistoricalBytes() {
      byte[] b = new byte[this.nHistorical];
      System.arraycopy(this.atr, this.startHistorical, b, 0, this.nHistorical);
      return b;
   }

   public String toString() {
      return "ATR: " + this.atr.length + " bytes";
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ATR)) {
         return false;
      } else {
         ATR other = (ATR)obj;
         return Arrays.equals(this.atr, other.atr);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.atr);
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      this.atr = (byte[])((byte[])in.readUnshared());
      this.parse();
   }
}
