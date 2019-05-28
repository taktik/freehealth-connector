package sun.net.httpserver;

import com.sun.net.httpserver.Headers;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

class Request {
   static final int BUF_LEN = 2048;
   static final byte CR = 13;
   static final byte LF = 10;
   private String startLine;
   private SocketChannel chan;
   private InputStream is;
   private OutputStream os;
   char[] buf = new char[2048];
   int pos;
   StringBuffer lineBuf;
   Headers hdrs = null;

   Request(InputStream rawInputStream, OutputStream rawout) throws IOException {
      this.chan = this.chan;
      this.is = rawInputStream;
      this.os = rawout;

      do {
         this.startLine = this.readLine();
         if (this.startLine == null) {
            return;
         }
      } while(this.startLine != null && this.startLine.equals(""));

   }

   public InputStream inputStream() {
      return this.is;
   }

   public OutputStream outputStream() {
      return this.os;
   }

   public String readLine() throws IOException {
      boolean gotCR = false;
      boolean gotLF = false;
      this.pos = 0;
      this.lineBuf = new StringBuffer();

      while(!gotLF) {
         int c = this.is.read();
         if (c == -1) {
            return null;
         }

         if (gotCR) {
            if (c == 10) {
               gotLF = true;
            } else {
               gotCR = false;
               this.consume(13);
               this.consume(c);
            }
         } else if (c == 13) {
            gotCR = true;
         } else {
            this.consume(c);
         }
      }

      this.lineBuf.append(this.buf, 0, this.pos);
      return new String(this.lineBuf);
   }

   private void consume(int c) {
      if (this.pos == 2048) {
         this.lineBuf.append(this.buf);
         this.pos = 0;
      }

      this.buf[this.pos++] = (char)c;
   }

   public String requestLine() {
      return this.startLine;
   }

   Headers headers() throws IOException {
      if (this.hdrs != null) {
         return this.hdrs;
      } else {
         this.hdrs = new Headers();
         char[] s = new char[10];
         int len = 0;
         int firstc = this.is.read();
         int keyend;
         if (firstc == 13 || firstc == 10) {
            keyend = this.is.read();
            if (keyend == 13 || keyend == 10) {
               return this.hdrs;
            }

            s[0] = (char)firstc;
            len = 1;
            firstc = keyend;
         }

         while(firstc != 10 && firstc != 13 && firstc >= 0) {
            keyend = -1;
            boolean inKey = firstc > 32;
            int len = len + 1;
            s[len] = (char)firstc;

            label112:
            while(true) {
               int c;
               if ((c = this.is.read()) < 0) {
                  firstc = -1;
                  break;
               }

               switch(c) {
               case 9:
                  c = 32;
               case 32:
                  inKey = false;
                  break;
               case 10:
               case 13:
                  firstc = this.is.read();
                  if (c == 13 && firstc == 10) {
                     firstc = this.is.read();
                     if (firstc == 13) {
                        firstc = this.is.read();
                     }
                  }

                  if (firstc == 10 || firstc == 13 || firstc > 32) {
                     break label112;
                  }

                  c = 32;
                  break;
               case 58:
                  if (inKey && len > 0) {
                     keyend = len;
                  }

                  inKey = false;
               }

               if (len >= s.length) {
                  char[] ns = new char[s.length * 2];
                  System.arraycopy(s, 0, ns, 0, len);
                  s = ns;
               }

               s[len++] = (char)c;
            }

            while(len > 0 && s[len - 1] <= ' ') {
               --len;
            }

            String k;
            if (keyend <= 0) {
               k = null;
               keyend = 0;
            } else {
               k = String.copyValueOf(s, 0, keyend);
               if (keyend < len && s[keyend] == ':') {
                  ++keyend;
               }

               while(keyend < len && s[keyend] <= ' ') {
                  ++keyend;
               }
            }

            String v;
            if (keyend >= len) {
               v = new String();
            } else {
               v = String.copyValueOf(s, keyend, len - keyend);
            }

            if (this.hdrs.size() >= ServerConfig.getMaxReqHeaders()) {
               throw new IOException("Maximum number of request headers (sun.net.httpserver.maxReqHeaders) exceeded, " + ServerConfig.getMaxReqHeaders() + ".");
            }

            this.hdrs.add(k, v);
            len = 0;
         }

         return this.hdrs;
      }
   }

   static class WriteStream extends OutputStream {
      SocketChannel channel;
      ByteBuffer buf;
      SelectionKey key;
      boolean closed;
      byte[] one;
      ServerImpl server;

      public WriteStream(ServerImpl server, SocketChannel channel) throws IOException {
         this.channel = channel;
         this.server = server;

         assert channel.isBlocking();

         this.closed = false;
         this.one = new byte[1];
         this.buf = ByteBuffer.allocate(4096);
      }

      public synchronized void write(int b) throws IOException {
         this.one[0] = (byte)b;
         this.write(this.one, 0, 1);
      }

      public synchronized void write(byte[] b) throws IOException {
         this.write(b, 0, b.length);
      }

      public synchronized void write(byte[] b, int off, int len) throws IOException {
         int l = len;
         if (this.closed) {
            throw new IOException("stream is closed");
         } else {
            int cap = this.buf.capacity();
            int n;
            if (cap < len) {
               n = len - cap;
               this.buf = ByteBuffer.allocate(2 * (cap + n));
            }

            this.buf.clear();
            this.buf.put(b, off, len);
            this.buf.flip();

            do {
               if ((n = this.channel.write(this.buf)) >= l) {
                  return;
               }

               l -= n;
            } while(l != 0);

         }
      }

      public void close() throws IOException {
         if (!this.closed) {
            this.channel.close();
            this.closed = true;
         }
      }
   }

   static class ReadStream extends InputStream {
      SocketChannel channel;
      ByteBuffer chanbuf;
      byte[] one;
      private boolean closed = false;
      private boolean eof = false;
      ByteBuffer markBuf;
      boolean marked;
      boolean reset;
      int readlimit;
      static long readTimeout;
      ServerImpl server;
      static final int BUFSIZE = 8192;

      public ReadStream(ServerImpl server, SocketChannel chan) throws IOException {
         this.channel = chan;
         this.server = server;
         this.chanbuf = ByteBuffer.allocate(8192);
         this.chanbuf.clear();
         this.one = new byte[1];
         this.closed = false;
         this.marked = false;
         this.reset = false;
      }

      public synchronized int read(byte[] b) throws IOException {
         return this.read(b, 0, b.length);
      }

      public synchronized int read() throws IOException {
         int result = this.read(this.one, 0, 1);
         return result == 1 ? this.one[0] & 255 : -1;
      }

      public synchronized int read(byte[] b, int off, int srclen) throws IOException {
         if (this.closed) {
            throw new IOException("Stream closed");
         } else if (this.eof) {
            return -1;
         } else {
            assert this.channel.isBlocking();

            if (off >= 0 && srclen >= 0 && srclen <= b.length - off) {
               int willreturn;
               if (this.reset) {
                  int canreturn = this.markBuf.remaining();
                  willreturn = canreturn > srclen ? srclen : canreturn;
                  this.markBuf.get(b, off, willreturn);
                  if (canreturn == willreturn) {
                     this.reset = false;
                  }
               } else {
                  this.chanbuf.clear();
                  if (srclen < 8192) {
                     this.chanbuf.limit(srclen);
                  }

                  do {
                     willreturn = this.channel.read(this.chanbuf);
                  } while(willreturn == 0);

                  if (willreturn == -1) {
                     this.eof = true;
                     return -1;
                  }

                  this.chanbuf.flip();
                  this.chanbuf.get(b, off, willreturn);
                  if (this.marked) {
                     try {
                        this.markBuf.put(b, off, willreturn);
                     } catch (BufferOverflowException var7) {
                        this.marked = false;
                     }
                  }
               }

               return willreturn;
            } else {
               throw new IndexOutOfBoundsException();
            }
         }
      }

      public boolean markSupported() {
         return true;
      }

      public synchronized int available() throws IOException {
         if (this.closed) {
            throw new IOException("Stream is closed");
         } else if (this.eof) {
            return -1;
         } else {
            return this.reset ? this.markBuf.remaining() : this.chanbuf.remaining();
         }
      }

      public void close() throws IOException {
         if (!this.closed) {
            this.channel.close();
            this.closed = true;
         }
      }

      public synchronized void mark(int readlimit) {
         if (!this.closed) {
            this.readlimit = readlimit;
            this.markBuf = ByteBuffer.allocate(readlimit);
            this.marked = true;
            this.reset = false;
         }
      }

      public synchronized void reset() throws IOException {
         if (!this.closed) {
            if (!this.marked) {
               throw new IOException("Stream not marked");
            } else {
               this.marked = false;
               this.reset = true;
               this.markBuf.flip();
            }
         }
      }
   }
}
