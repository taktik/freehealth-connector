package sun.net.httpserver;

import java.io.IOException;
import java.io.OutputStream;

class PlaceholderOutputStream extends OutputStream {
   OutputStream wrapped;

   PlaceholderOutputStream(OutputStream os) {
      this.wrapped = os;
   }

   void setWrappedStream(OutputStream os) {
      this.wrapped = os;
   }

   boolean isWrapped() {
      return this.wrapped != null;
   }

   private void checkWrap() throws IOException {
      if (this.wrapped == null) {
         throw new IOException("response headers not sent yet");
      }
   }

   public void write(int b) throws IOException {
      this.checkWrap();
      this.wrapped.write(b);
   }

   public void write(byte[] b) throws IOException {
      this.checkWrap();
      this.wrapped.write(b);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.checkWrap();
      this.wrapped.write(b, off, len);
   }

   public void flush() throws IOException {
      this.checkWrap();
      this.wrapped.flush();
   }

   public void close() throws IOException {
      this.checkWrap();
      this.wrapped.close();
   }
}
