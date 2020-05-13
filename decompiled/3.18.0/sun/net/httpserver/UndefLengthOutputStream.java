package sun.net.httpserver;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class UndefLengthOutputStream extends FilterOutputStream {
   private boolean closed = false;
   ExchangeImpl t;

   UndefLengthOutputStream(ExchangeImpl t, OutputStream src) {
      super(src);
      this.t = t;
   }

   public void write(int b) throws IOException {
      if (this.closed) {
         throw new IOException("stream closed");
      } else {
         this.out.write(b);
      }
   }

   public void write(byte[] b, int off, int len) throws IOException {
      if (this.closed) {
         throw new IOException("stream closed");
      } else {
         this.out.write(b, off, len);
      }
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.closed = true;
         this.flush();
         LeftOverInputStream is = this.t.getOriginalInputStream();
         if (!is.isClosed()) {
            try {
               is.close();
            } catch (IOException var3) {
            }
         }

         WriteFinishedEvent e = new WriteFinishedEvent(this.t);
         this.t.getHttpContext().getServerImpl().addEvent(e);
      }
   }
}
