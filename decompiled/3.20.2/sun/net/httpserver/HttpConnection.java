package sun.net.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

class HttpConnection {
   HttpContextImpl context;
   SSLEngine engine;
   SSLContext sslContext;
   SSLStreams sslStreams;
   InputStream i;
   InputStream raw;
   OutputStream rawout;
   SocketChannel chan;
   SelectionKey selectionKey;
   String protocol;
   long time;
   volatile long creationTime;
   volatile long rspStartedTime;
   int remaining;
   boolean closed = false;
   Logger logger;
   volatile HttpConnection.State state;

   public String toString() {
      String s = null;
      if (this.chan != null) {
         s = this.chan.toString();
      }

      return s;
   }

   void setChannel(SocketChannel c) {
      this.chan = c;
   }

   void setContext(HttpContextImpl ctx) {
      this.context = ctx;
   }

   HttpConnection.State getState() {
      return this.state;
   }

   void setState(HttpConnection.State s) {
      this.state = s;
   }

   void setParameters(InputStream in, OutputStream rawout, SocketChannel chan, SSLEngine engine, SSLStreams sslStreams, SSLContext sslContext, String protocol, HttpContextImpl context, InputStream raw) {
      this.context = context;
      this.i = in;
      this.rawout = rawout;
      this.raw = raw;
      this.protocol = protocol;
      this.engine = engine;
      this.chan = chan;
      this.sslContext = sslContext;
      this.sslStreams = sslStreams;
      this.logger = context.getLogger();
   }

   SocketChannel getChannel() {
      return this.chan;
   }

   synchronized void close() {
      if (!this.closed) {
         this.closed = true;
         if (this.logger != null && this.chan != null) {
            this.logger.finest("Closing connection: " + this.chan.toString());
         }

         if (!this.chan.isOpen()) {
            ServerImpl.dprint("Channel already closed");
         } else {
            try {
               if (this.raw != null) {
                  this.raw.close();
               }
            } catch (IOException var5) {
               ServerImpl.dprint((Exception)var5);
            }

            try {
               if (this.rawout != null) {
                  this.rawout.close();
               }
            } catch (IOException var4) {
               ServerImpl.dprint((Exception)var4);
            }

            try {
               if (this.sslStreams != null) {
                  this.sslStreams.close();
               }
            } catch (IOException var3) {
               ServerImpl.dprint((Exception)var3);
            }

            try {
               this.chan.close();
            } catch (IOException var2) {
               ServerImpl.dprint((Exception)var2);
            }

         }
      }
   }

   void setRemaining(int r) {
      this.remaining = r;
   }

   int getRemaining() {
      return this.remaining;
   }

   SelectionKey getSelectionKey() {
      return this.selectionKey;
   }

   InputStream getInputStream() {
      return this.i;
   }

   OutputStream getRawOutputStream() {
      return this.rawout;
   }

   String getProtocol() {
      return this.protocol;
   }

   SSLEngine getSSLEngine() {
      return this.engine;
   }

   SSLContext getSSLContext() {
      return this.sslContext;
   }

   HttpContextImpl getHttpContext() {
      return this.context;
   }

   public static enum State {
      IDLE,
      REQUEST,
      RESPONSE;
   }
}
