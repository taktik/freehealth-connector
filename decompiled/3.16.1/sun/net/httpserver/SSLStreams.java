package sun.net.httpserver;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;

class SSLStreams {
   SSLContext sslctx;
   SocketChannel chan;
   TimeSource time;
   ServerImpl server;
   SSLEngine engine;
   SSLStreams.EngineWrapper wrapper;
   SSLStreams.OutputStream os;
   SSLStreams.InputStream is;
   Lock handshaking = new ReentrantLock();
   int app_buf_size;
   int packet_buf_size;

   SSLStreams(ServerImpl server, SSLContext sslctx, SocketChannel chan) throws IOException {
      this.server = server;
      this.time = server;
      this.sslctx = sslctx;
      this.chan = chan;
      InetSocketAddress addr = (InetSocketAddress)chan.socket().getRemoteSocketAddress();
      this.engine = sslctx.createSSLEngine(addr.getHostName(), addr.getPort());
      this.engine.setUseClientMode(false);
      HttpsConfigurator cfg = server.getHttpsConfigurator();
      this.configureEngine(cfg, addr);
      this.wrapper = new SSLStreams.EngineWrapper(chan, this.engine);
   }

   private void configureEngine(HttpsConfigurator cfg, InetSocketAddress addr) {
      if (cfg != null) {
         SSLStreams.Parameters params = new SSLStreams.Parameters(cfg, addr);
         cfg.configure(params);
         SSLParameters sslParams = params.getSSLParameters();
         if (sslParams != null) {
            this.engine.setSSLParameters(sslParams);
         } else {
            if (params.getCipherSuites() != null) {
               try {
                  this.engine.setEnabledCipherSuites(params.getCipherSuites());
               } catch (IllegalArgumentException var7) {
                  ;
               }
            }

            this.engine.setNeedClientAuth(params.getNeedClientAuth());
            this.engine.setWantClientAuth(params.getWantClientAuth());
            if (params.getProtocols() != null) {
               try {
                  this.engine.setEnabledProtocols(params.getProtocols());
               } catch (IllegalArgumentException var6) {
                  ;
               }
            }
         }
      }

   }

   void close() throws IOException {
      this.wrapper.close();
   }

   SSLStreams.InputStream getInputStream() throws IOException {
      if (this.is == null) {
         this.is = new SSLStreams.InputStream();
      }

      return this.is;
   }

   SSLStreams.OutputStream getOutputStream() throws IOException {
      if (this.os == null) {
         this.os = new SSLStreams.OutputStream();
      }

      return this.os;
   }

   SSLEngine getSSLEngine() {
      return this.engine;
   }

   void beginHandshake() throws SSLException {
      this.engine.beginHandshake();
   }

   private ByteBuffer allocate(SSLStreams.BufType type) {
      return this.allocate(type, -1);
   }

   private ByteBuffer allocate(SSLStreams.BufType type, int len) {
      assert this.engine != null;

      synchronized(this) {
         int size;
         SSLSession sess;
         if (type == SSLStreams.BufType.PACKET) {
            if (this.packet_buf_size == 0) {
               sess = this.engine.getSession();
               this.packet_buf_size = sess.getPacketBufferSize();
            }

            if (len > this.packet_buf_size) {
               this.packet_buf_size = len;
            }

            size = this.packet_buf_size;
         } else {
            if (this.app_buf_size == 0) {
               sess = this.engine.getSession();
               this.app_buf_size = sess.getApplicationBufferSize();
            }

            if (len > this.app_buf_size) {
               this.app_buf_size = len;
            }

            size = this.app_buf_size;
         }

         return ByteBuffer.allocate(size);
      }
   }

   private ByteBuffer realloc(ByteBuffer b, boolean flip, SSLStreams.BufType type) {
      synchronized(this) {
         int nsize = 2 * b.capacity();
         ByteBuffer n = this.allocate(type, nsize);
         if (flip) {
            b.flip();
         }

         n.put(b);
         return n;
      }
   }

   public SSLStreams.WrapperResult sendData(ByteBuffer src) throws IOException {
      SSLStreams.WrapperResult r = null;

      while(src.remaining() > 0) {
         r = this.wrapper.wrapAndSend(src);
         Status status = r.result.getStatus();
         if (status == Status.CLOSED) {
            this.doClosure();
            return r;
         }

         HandshakeStatus hs_status = r.result.getHandshakeStatus();
         if (hs_status != HandshakeStatus.FINISHED && hs_status != HandshakeStatus.NOT_HANDSHAKING) {
            this.doHandshake(hs_status);
         }
      }

      return r;
   }

   public SSLStreams.WrapperResult recvData(ByteBuffer dst) throws IOException {
      SSLStreams.WrapperResult r = null;

      assert dst.position() == 0;

      while(dst.position() == 0) {
         r = this.wrapper.recvAndUnwrap(dst);
         dst = r.buf != dst ? r.buf : dst;
         Status status = r.result.getStatus();
         if (status == Status.CLOSED) {
            this.doClosure();
            return r;
         }

         HandshakeStatus hs_status = r.result.getHandshakeStatus();
         if (hs_status != HandshakeStatus.FINISHED && hs_status != HandshakeStatus.NOT_HANDSHAKING) {
            this.doHandshake(hs_status);
         }
      }

      dst.flip();
      return r;
   }

   void doClosure() throws IOException {
      try {
         this.handshaking.lock();
         ByteBuffer tmp = this.allocate(SSLStreams.BufType.APPLICATION);

         SSLStreams.WrapperResult r;
         do {
            tmp.clear();
            tmp.flip();
            r = this.wrapper.wrapAndSendX(tmp, true);
         } while(r.result.getStatus() != Status.CLOSED);
      } finally {
         this.handshaking.unlock();
      }

   }

   void doHandshake(HandshakeStatus hs_status) throws IOException {
      try {
         this.handshaking.lock();

         SSLStreams.WrapperResult r;
         for(ByteBuffer tmp = this.allocate(SSLStreams.BufType.APPLICATION); hs_status != HandshakeStatus.FINISHED && hs_status != HandshakeStatus.NOT_HANDSHAKING; hs_status = r.result.getHandshakeStatus()) {
            r = null;
            Runnable task;
            switch(hs_status) {
            case NEED_TASK:
               while((task = this.engine.getDelegatedTask()) != null) {
                  task.run();
               }
            case NEED_WRAP:
               break;
            case NEED_UNWRAP:
               tmp.clear();
               r = this.wrapper.recvAndUnwrap(tmp);
               if (r.buf != tmp) {
                  tmp = r.buf;
               }

               assert tmp.position() == 0;
            default:
               continue;
            }

            tmp.clear();
            tmp.flip();
            r = this.wrapper.wrapAndSend(tmp);
         }
      } finally {
         this.handshaking.unlock();
      }

   }

   class OutputStream extends java.io.OutputStream {
      ByteBuffer buf;
      boolean closed = false;
      byte[] single = new byte[1];

      OutputStream() {
         this.buf = SSLStreams.this.allocate(SSLStreams.BufType.APPLICATION);
      }

      public void write(int b) throws IOException {
         this.single[0] = (byte)b;
         this.write(this.single, 0, 1);
      }

      public void write(byte[] b) throws IOException {
         this.write(b, 0, b.length);
      }

      public void write(byte[] b, int off, int len) throws IOException {
         if (this.closed) {
            throw new IOException("output stream is closed");
         } else {
            while(len > 0) {
               int l = len > this.buf.capacity() ? this.buf.capacity() : len;
               this.buf.clear();
               this.buf.put(b, off, l);
               len -= l;
               off += l;
               this.buf.flip();
               SSLStreams.WrapperResult r = SSLStreams.this.sendData(this.buf);
               if (r.result.getStatus() == Status.CLOSED) {
                  this.closed = true;
                  if (len > 0) {
                     throw new IOException("output stream is closed");
                  }
               }
            }

         }
      }

      public void flush() throws IOException {
      }

      public void close() throws IOException {
         SSLStreams.WrapperResult r = null;
         SSLStreams.this.engine.closeOutbound();
         this.closed = true;
         HandshakeStatus stat = HandshakeStatus.NEED_WRAP;
         this.buf.clear();

         while(stat == HandshakeStatus.NEED_WRAP) {
            r = SSLStreams.this.wrapper.wrapAndSend(this.buf);
            stat = r.result.getHandshakeStatus();
         }

         assert r.result.getStatus() == Status.CLOSED;

      }
   }

   class InputStream extends java.io.InputStream {
      ByteBuffer bbuf;
      boolean closed = false;
      boolean eof = false;
      boolean needData = true;
      byte[] single = new byte[1];

      InputStream() {
         this.bbuf = SSLStreams.this.allocate(SSLStreams.BufType.APPLICATION);
      }

      public int read(byte[] buf, int off, int len) throws IOException {
         if (this.closed) {
            throw new IOException("SSL stream is closed");
         } else if (this.eof) {
            return 0;
         } else {
            int available = 0;
            if (!this.needData) {
               available = this.bbuf.remaining();
               this.needData = available == 0;
            }

            if (this.needData) {
               this.bbuf.clear();
               SSLStreams.WrapperResult r = SSLStreams.this.recvData(this.bbuf);
               this.bbuf = r.buf == this.bbuf ? this.bbuf : r.buf;
               available = this.bbuf.remaining();
               if (available == 0) {
                  this.eof = true;
                  return 0;
               }

               this.needData = false;
            }

            if (len > available) {
               len = available;
            }

            this.bbuf.get(buf, off, len);
            return len;
         }
      }

      public int available() throws IOException {
         return this.bbuf.remaining();
      }

      public boolean markSupported() {
         return false;
      }

      public void reset() throws IOException {
         throw new IOException("mark/reset not supported");
      }

      public long skip(long s) throws IOException {
         int n = (int)s;
         if (this.closed) {
            throw new IOException("SSL stream is closed");
         } else if (this.eof) {
            return 0L;
         } else {
            int ret;
            SSLStreams.WrapperResult r;
            for(ret = n; n > 0; this.bbuf = r.buf == this.bbuf ? this.bbuf : r.buf) {
               if (this.bbuf.remaining() >= n) {
                  this.bbuf.position(this.bbuf.position() + n);
                  return (long)ret;
               }

               n -= this.bbuf.remaining();
               this.bbuf.clear();
               r = SSLStreams.this.recvData(this.bbuf);
            }

            return (long)ret;
         }
      }

      public void close() throws IOException {
         this.eof = true;
         SSLStreams.this.engine.closeInbound();
      }

      public int read(byte[] buf) throws IOException {
         return this.read(buf, 0, buf.length);
      }

      public int read() throws IOException {
         int n = this.read(this.single, 0, 1);
         return n == 0 ? -1 : this.single[0] & 255;
      }
   }

   class EngineWrapper {
      SocketChannel chan;
      SSLEngine engine;
      Object wrapLock;
      Object unwrapLock;
      ByteBuffer unwrap_src;
      ByteBuffer wrap_dst;
      boolean closed = false;
      int u_remaining;

      EngineWrapper(SocketChannel chan, SSLEngine engine) throws IOException {
         this.chan = chan;
         this.engine = engine;
         this.wrapLock = new Object();
         this.unwrapLock = new Object();
         this.unwrap_src = SSLStreams.this.allocate(SSLStreams.BufType.PACKET);
         this.wrap_dst = SSLStreams.this.allocate(SSLStreams.BufType.PACKET);
      }

      void close() throws IOException {
      }

      SSLStreams.WrapperResult wrapAndSend(ByteBuffer src) throws IOException {
         return this.wrapAndSendX(src, false);
      }

      SSLStreams.WrapperResult wrapAndSendX(ByteBuffer src, boolean ignoreClose) throws IOException {
         if (this.closed && !ignoreClose) {
            throw new IOException("Engine is closed");
         } else {
            SSLStreams.WrapperResult r = SSLStreams.this.new WrapperResult();
            Object var5 = this.wrapLock;
            synchronized(this.wrapLock) {
               this.wrap_dst.clear();

               Status status;
               do {
                  r.result = this.engine.wrap(src, this.wrap_dst);
                  status = r.result.getStatus();
                  if (status == Status.BUFFER_OVERFLOW) {
                     this.wrap_dst = SSLStreams.this.realloc(this.wrap_dst, true, SSLStreams.BufType.PACKET);
                  }
               } while(status == Status.BUFFER_OVERFLOW);

               if (status == Status.CLOSED && !ignoreClose) {
                  this.closed = true;
                  return r;
               } else {
                  if (r.result.bytesProduced() > 0) {
                     this.wrap_dst.flip();
                     int l = this.wrap_dst.remaining();

                     assert l == r.result.bytesProduced();

                     while(l > 0) {
                        l -= this.chan.write(this.wrap_dst);
                     }
                  }

                  return r;
               }
            }
         }
      }

      SSLStreams.WrapperResult recvAndUnwrap(ByteBuffer dst) throws IOException {
         Status status = Status.OK;
         SSLStreams.WrapperResult r = SSLStreams.this.new WrapperResult();
         r.buf = dst;
         if (this.closed) {
            throw new IOException("Engine is closed");
         } else {
            boolean needData;
            if (this.u_remaining > 0) {
               this.unwrap_src.compact();
               this.unwrap_src.flip();
               needData = false;
            } else {
               this.unwrap_src.clear();
               needData = true;
            }

            Object var5 = this.unwrapLock;
            synchronized(this.unwrapLock) {
               do {
                  if (needData) {
                     int x;
                     do {
                        x = this.chan.read(this.unwrap_src);
                     } while(x == 0);

                     if (x == -1) {
                        throw new IOException("connection closed for reading");
                     }

                     this.unwrap_src.flip();
                  }

                  r.result = this.engine.unwrap(this.unwrap_src, r.buf);
                  status = r.result.getStatus();
                  if (status == Status.BUFFER_UNDERFLOW) {
                     if (this.unwrap_src.limit() == this.unwrap_src.capacity()) {
                        this.unwrap_src = SSLStreams.this.realloc(this.unwrap_src, false, SSLStreams.BufType.PACKET);
                     } else {
                        this.unwrap_src.position(this.unwrap_src.limit());
                        this.unwrap_src.limit(this.unwrap_src.capacity());
                     }

                     needData = true;
                  } else if (status == Status.BUFFER_OVERFLOW) {
                     r.buf = SSLStreams.this.realloc(r.buf, true, SSLStreams.BufType.APPLICATION);
                     needData = false;
                  } else if (status == Status.CLOSED) {
                     this.closed = true;
                     r.buf.flip();
                     return r;
                  }
               } while(status != Status.OK);
            }

            this.u_remaining = this.unwrap_src.remaining();
            return r;
         }
      }
   }

   static enum BufType {
      PACKET,
      APPLICATION;
   }

   class WrapperResult {
      SSLEngineResult result;
      ByteBuffer buf;
   }

   class Parameters extends HttpsParameters {
      InetSocketAddress addr;
      HttpsConfigurator cfg;
      SSLParameters params;

      Parameters(HttpsConfigurator cfg, InetSocketAddress addr) {
         this.addr = addr;
         this.cfg = cfg;
      }

      public InetSocketAddress getClientAddress() {
         return this.addr;
      }

      public HttpsConfigurator getHttpsConfigurator() {
         return this.cfg;
      }

      public void setSSLParameters(SSLParameters p) {
         this.params = p;
      }

      SSLParameters getSSLParameters() {
         return this.params;
      }
   }
}
