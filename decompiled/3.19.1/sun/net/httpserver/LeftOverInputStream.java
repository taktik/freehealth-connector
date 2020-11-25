package sun.net.httpserver;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

abstract class LeftOverInputStream extends FilterInputStream {
   ExchangeImpl t;
   ServerImpl server;
   protected boolean closed = false;
   protected boolean eof = false;
   byte[] one = new byte[1];

   public LeftOverInputStream(ExchangeImpl t, InputStream src) {
      super(src);
      this.t = t;
      this.server = t.getServerImpl();
   }

   public boolean isDataBuffered() throws IOException {
      assert this.eof;

      return super.available() > 0;
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.closed = true;
         if (!this.eof) {
            this.eof = this.drain(ServerConfig.getDrainAmount());
         }

      }
   }

   public boolean isClosed() {
      return this.closed;
   }

   public boolean isEOF() {
      return this.eof;
   }

   protected abstract int readImpl(byte[] var1, int var2, int var3) throws IOException;

   public synchronized int read() throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         int c = this.readImpl(this.one, 0, 1);
         return c != -1 && c != 0 ? this.one[0] & 255 : c;
      }
   }

   public synchronized int read(byte[] b, int off, int len) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         return this.readImpl(b, off, len);
      }
   }

   public boolean drain(long l) throws IOException {
      int bufSize = 2048;

      long len;
      for(byte[] db = new byte[bufSize]; l > 0L; l -= len) {
         len = (long)this.readImpl(db, 0, bufSize);
         if (len == -1L) {
            this.eof = true;
            return true;
         }
      }

      return false;
   }
}
