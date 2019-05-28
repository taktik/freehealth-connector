package sun.net.httpserver;

import java.io.IOException;
import java.io.InputStream;

class ChunkedInputStream extends LeftOverInputStream {
   private int remaining;
   private boolean needToReadHeader = true;
   static final char CR = '\r';
   static final char LF = '\n';

   ChunkedInputStream(ExchangeImpl t, InputStream src) {
      super(t, src);
   }

   private int numeric(char[] arr, int nchars) throws IOException {
      assert arr.length >= nchars;

      int len = 0;

      for(int i = 0; i < nchars; ++i) {
         char c = arr[i];
         int val = false;
         int val;
         if (c >= '0' && c <= '9') {
            val = c - 48;
         } else if (c >= 'a' && c <= 'f') {
            val = c - 97 + 10;
         } else {
            if (c < 'A' || c > 'F') {
               throw new IOException("invalid chunk length");
            }

            val = c - 65 + 10;
         }

         len = len * 16 + val;
      }

      return len;
   }

   private int readChunkHeader() throws IOException {
      boolean gotCR = false;
      char[] len_arr = new char[16];
      int len_size = 0;
      boolean end_of_len = false;

      int c;
      while((c = this.in.read()) != -1) {
         char ch = (char)c;
         if (len_size == len_arr.length - 1) {
            throw new IOException("invalid chunk header");
         }

         if (gotCR) {
            if (ch == '\n') {
               int l = this.numeric(len_arr, len_size);
               return l;
            }

            gotCR = false;
            if (!end_of_len) {
               len_arr[len_size++] = ch;
            }
         } else if (ch == '\r') {
            gotCR = true;
         } else if (ch == ';') {
            end_of_len = true;
         } else if (!end_of_len) {
            len_arr[len_size++] = ch;
         }
      }

      throw new IOException("end of stream reading chunk header");
   }

   protected int readImpl(byte[] b, int off, int len) throws IOException {
      if (this.eof) {
         return -1;
      } else {
         if (this.needToReadHeader) {
            this.remaining = this.readChunkHeader();
            if (this.remaining == 0) {
               this.eof = true;
               this.consumeCRLF();
               this.t.getServerImpl().requestCompleted(this.t.getConnection());
               return -1;
            }

            this.needToReadHeader = false;
         }

         if (len > this.remaining) {
            len = this.remaining;
         }

         int n = this.in.read(b, off, len);
         if (n > -1) {
            this.remaining -= n;
         }

         if (this.remaining == 0) {
            this.needToReadHeader = true;
            this.consumeCRLF();
         }

         return n;
      }
   }

   private void consumeCRLF() throws IOException {
      char c = (char)this.in.read();
      if (c != '\r') {
         throw new IOException("invalid chunk end");
      } else {
         c = (char)this.in.read();
         if (c != '\n') {
            throw new IOException("invalid chunk end");
         }
      }
   }

   public int available() throws IOException {
      if (!this.eof && !this.closed) {
         int n = this.in.available();
         return n > this.remaining ? this.remaining : n;
      } else {
         return 0;
      }
   }

   public boolean isDataBuffered() throws IOException {
      assert this.eof;

      return this.in.available() > 0;
   }

   public boolean markSupported() {
      return false;
   }

   public void mark(int l) {
   }

   public void reset() throws IOException {
      throw new IOException("mark/reset not supported");
   }
}
