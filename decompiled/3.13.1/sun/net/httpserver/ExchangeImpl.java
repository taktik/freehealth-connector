package sun.net.httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Map.Entry;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;

class ExchangeImpl {
   Headers reqHdrs;
   Headers rspHdrs;
   Request req;
   String method;
   boolean writefinished;
   URI uri;
   HttpConnection connection;
   long reqContentLen;
   long rspContentLen;
   InputStream ris;
   OutputStream ros;
   Thread thread;
   boolean close;
   boolean closed;
   boolean http10 = false;
   private static final String pattern = "EEE, dd MMM yyyy HH:mm:ss zzz";
   private static final TimeZone gmtTZ = TimeZone.getTimeZone("GMT");
   private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
      protected DateFormat initialValue() {
         DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
         df.setTimeZone(ExchangeImpl.gmtTZ);
         return df;
      }
   };
   private static final String HEAD = "HEAD";
   InputStream uis;
   OutputStream uos;
   LeftOverInputStream uis_orig;
   PlaceholderOutputStream uos_orig;
   boolean sentHeaders;
   Map<String, Object> attributes;
   int rcode = -1;
   HttpPrincipal principal;
   ServerImpl server;
   private byte[] rspbuf = new byte[128];

   ExchangeImpl(String m, URI u, Request req, long len, HttpConnection connection) throws IOException {
      this.req = req;
      this.reqHdrs = req.headers();
      this.rspHdrs = new Headers();
      this.method = m;
      this.uri = u;
      this.connection = connection;
      this.reqContentLen = len;
      this.ros = req.outputStream();
      this.ris = req.inputStream();
      this.server = this.getServerImpl();
      this.server.startExchange();
   }

   public Headers getRequestHeaders() {
      return new UnmodifiableHeaders(this.reqHdrs);
   }

   public Headers getResponseHeaders() {
      return this.rspHdrs;
   }

   public URI getRequestURI() {
      return this.uri;
   }

   public String getRequestMethod() {
      return this.method;
   }

   public HttpContextImpl getHttpContext() {
      return this.connection.getHttpContext();
   }

   private boolean isHeadRequest() {
      return "HEAD".equals(this.getRequestMethod());
   }

   public void close() {
      if (!this.closed) {
         this.closed = true;

         try {
            if (this.uis_orig == null || this.uos == null) {
               this.connection.close();
               return;
            }

            if (!this.uos_orig.isWrapped()) {
               this.connection.close();
               return;
            }

            if (!this.uis_orig.isClosed()) {
               this.uis_orig.close();
            }

            this.uos.close();
         } catch (IOException var2) {
            this.connection.close();
         }

      }
   }

   public InputStream getRequestBody() {
      if (this.uis != null) {
         return this.uis;
      } else {
         if (this.reqContentLen == -1L) {
            this.uis_orig = new ChunkedInputStream(this, this.ris);
            this.uis = this.uis_orig;
         } else {
            this.uis_orig = new FixedLengthInputStream(this, this.ris, this.reqContentLen);
            this.uis = this.uis_orig;
         }

         return this.uis;
      }
   }

   LeftOverInputStream getOriginalInputStream() {
      return this.uis_orig;
   }

   public int getResponseCode() {
      return this.rcode;
   }

   public OutputStream getResponseBody() {
      if (this.uos == null) {
         this.uos_orig = new PlaceholderOutputStream((OutputStream)null);
         this.uos = this.uos_orig;
      }

      return this.uos;
   }

   PlaceholderOutputStream getPlaceholderResponseBody() {
      this.getResponseBody();
      return this.uos_orig;
   }

   public void sendResponseHeaders(int rCode, long contentLen) throws IOException {
      if (this.sentHeaders) {
         throw new IOException("headers already sent");
      } else {
         this.rcode = rCode;
         String statusLine = "HTTP/1.1 " + rCode + Code.msg(rCode) + "\r\n";
         OutputStream tmpout = new BufferedOutputStream(this.ros);
         PlaceholderOutputStream o = this.getPlaceholderResponseBody();
         tmpout.write(this.bytes(statusLine, 0), 0, statusLine.length());
         boolean noContentToSend = false;
         this.rspHdrs.set("Date", ((DateFormat)dateFormat.get()).format(new Date()));
         Logger logger;
         String msg;
         if (rCode >= 100 && rCode < 200 || rCode == 204 || rCode == 304) {
            if (contentLen != -1L) {
               logger = this.server.getLogger();
               msg = "sendResponseHeaders: rCode = " + rCode + ": forcing contentLen = -1";
               logger.warning(msg);
            }

            contentLen = -1L;
         }

         if (this.isHeadRequest()) {
            if (contentLen >= 0L) {
               logger = this.server.getLogger();
               msg = "sendResponseHeaders: being invoked with a content length for a HEAD request";
               logger.warning(msg);
            }

            noContentToSend = true;
            contentLen = 0L;
         } else if (contentLen == 0L) {
            if (this.http10) {
               o.setWrappedStream(new UndefLengthOutputStream(this, this.ros));
               this.close = true;
            } else {
               this.rspHdrs.set("Transfer-encoding", "chunked");
               o.setWrappedStream(new ChunkedOutputStream(this, this.ros));
            }
         } else {
            if (contentLen == -1L) {
               noContentToSend = true;
               contentLen = 0L;
            }

            this.rspHdrs.set("Content-length", Long.toString(contentLen));
            o.setWrappedStream(new FixedLengthOutputStream(this, this.ros, contentLen));
         }

         this.write(this.rspHdrs, tmpout);
         this.rspContentLen = contentLen;
         tmpout.flush();
         tmpout = null;
         this.sentHeaders = true;
         if (noContentToSend) {
            WriteFinishedEvent e = new WriteFinishedEvent(this);
            this.server.addEvent(e);
            this.closed = true;
         }

         this.server.logReply(rCode, this.req.requestLine(), (String)null);
      }
   }

   void write(Headers map, OutputStream os) throws IOException {
      Set<Entry<String, List<String>>> entries = map.entrySet();
      Iterator i$ = entries.iterator();

      while(i$.hasNext()) {
         Entry<String, List<String>> entry = (Entry)i$.next();
         String key = (String)entry.getKey();
         List<String> values = (List)entry.getValue();
         Iterator i$ = values.iterator();

         while(i$.hasNext()) {
            String val = (String)i$.next();
            int i = key.length();
            byte[] buf = this.bytes(key, 2);
            buf[i++] = 58;
            buf[i++] = 32;
            os.write(buf, 0, i);
            buf = this.bytes(val, 2);
            i = val.length();
            buf[i++] = 13;
            buf[i++] = 10;
            os.write(buf, 0, i);
         }
      }

      os.write(13);
      os.write(10);
   }

   private byte[] bytes(String s, int extra) {
      int slen = s.length();
      if (slen + extra > this.rspbuf.length) {
         int diff = slen + extra - this.rspbuf.length;
         this.rspbuf = new byte[2 * (this.rspbuf.length + diff)];
      }

      char[] c = s.toCharArray();

      for(int i = 0; i < c.length; ++i) {
         this.rspbuf[i] = (byte)c[i];
      }

      return this.rspbuf;
   }

   public InetSocketAddress getRemoteAddress() {
      Socket s = this.connection.getChannel().socket();
      InetAddress ia = s.getInetAddress();
      int port = s.getPort();
      return new InetSocketAddress(ia, port);
   }

   public InetSocketAddress getLocalAddress() {
      Socket s = this.connection.getChannel().socket();
      InetAddress ia = s.getLocalAddress();
      int port = s.getLocalPort();
      return new InetSocketAddress(ia, port);
   }

   public String getProtocol() {
      String reqline = this.req.requestLine();
      int index = reqline.lastIndexOf(32);
      return reqline.substring(index + 1);
   }

   public SSLSession getSSLSession() {
      SSLEngine e = this.connection.getSSLEngine();
      return e == null ? null : e.getSession();
   }

   public Object getAttribute(String name) {
      if (name == null) {
         throw new NullPointerException("null name parameter");
      } else {
         if (this.attributes == null) {
            this.attributes = this.getHttpContext().getAttributes();
         }

         return this.attributes.get(name);
      }
   }

   public void setAttribute(String name, Object value) {
      if (name == null) {
         throw new NullPointerException("null name parameter");
      } else {
         if (this.attributes == null) {
            this.attributes = this.getHttpContext().getAttributes();
         }

         this.attributes.put(name, value);
      }
   }

   public void setStreams(InputStream i, OutputStream o) {
      assert this.uis != null;

      if (i != null) {
         this.uis = i;
      }

      if (o != null) {
         this.uos = o;
      }

   }

   HttpConnection getConnection() {
      return this.connection;
   }

   ServerImpl getServerImpl() {
      return this.getHttpContext().getServerImpl();
   }

   public HttpPrincipal getPrincipal() {
      return this.principal;
   }

   void setPrincipal(HttpPrincipal principal) {
      this.principal = principal;
   }

   static ExchangeImpl get(HttpExchange t) {
      if (t instanceof HttpExchangeImpl) {
         return ((HttpExchangeImpl)t).getExchangeImpl();
      } else {
         assert t instanceof HttpsExchangeImpl;

         return ((HttpsExchangeImpl)t).getExchangeImpl();
      }
   }
}
