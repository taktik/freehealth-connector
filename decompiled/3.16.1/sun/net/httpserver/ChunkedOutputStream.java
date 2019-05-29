package sun.net.httpserver;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class ChunkedOutputStream extends FilterOutputStream {
   private boolean closed = false;
   static final int CHUNK_SIZE = 4096;
   static final int OFFSET = 6;
   private int pos = 6;
   private int count = 0;
   private byte[] buf = new byte[4104];
   ExchangeImpl t;

   ChunkedOutputStream(ExchangeImpl t, OutputStream src) {
      super(src);
      this.t = t;
   }

   public void write(int b) throws IOException {
      if (this.closed) {
         throw new StreamClosedException();
      } else {
         this.buf[this.pos++] = (byte)b;
         ++this.count;
         if (this.count == 4096) {
            this.writeChunk();
         }

         assert this.count < 4096;

      }
   }

   public void write(byte[] b, int off, int len) throws IOException {
      if (this.closed) {
         throw new StreamClosedException();
      } else {
         int remain = 4096 - this.count;
         if (len > remain) {
            System.arraycopy(b, off, this.buf, this.pos, remain);
            this.count = 4096;
            this.writeChunk();
            len -= remain;
            off += remain;

            while(len >= 4096) {
               System.arraycopy(b, off, this.buf, 6, 4096);
               len -= 4096;
               off += 4096;
               this.count = 4096;
               this.writeChunk();
            }
         }

         if (len > 0) {
            System.arraycopy(b, off, this.buf, this.pos, len);
            this.count += len;
            this.pos += len;
         }

         if (this.count == 4096) {
            this.writeChunk();
         }

      }
   }

   private void writeChunk() throws IOException {
      char[] c = Integer.toHexString(this.count).toCharArray();
      int clen = c.length;
      int startByte = 4 - clen;

      int i;
      for(i = 0; i < clen; ++i) {
         this.buf[startByte + i] = (byte)c[i];
      }

      this.buf[startByte + i++] = 13;
      this.buf[startByte + i++] = 10;
      this.buf[startByte + i++ + this.count] = 13;
      this.buf[startByte + i++ + this.count] = 10;
      this.out.write(this.buf, startByte, i + this.count);
      this.count = 0;
      this.pos = 6;
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.flush();

         try {
            this.writeChunk();
            this.out.flush();
            LeftOverInputStream is = this.t.getOriginalInputStream();
            if (!is.isClosed()) {
               is.close();
            }
         } catch (IOException var5) {
            ;
         } finally {
            this.closed = true;
         }

         WriteFinishedEvent e = new WriteFinishedEvent(this.t);
         this.t.getHttpContext().getServerImpl().addEvent(e);
      }
   }

   public void flush() throws IOException {
      if (this.closed) {
         throw new StreamClosedException();
      } else {
         if (this.count > 0) {
            this.writeChunk();
         }

         this.out.flush();
      }
   }
}
