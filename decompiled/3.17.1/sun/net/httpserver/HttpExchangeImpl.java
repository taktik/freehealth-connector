package sun.net.httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

class HttpExchangeImpl extends HttpExchange {
   ExchangeImpl impl;

   HttpExchangeImpl(ExchangeImpl impl) {
      this.impl = impl;
   }

   public Headers getRequestHeaders() {
      return this.impl.getRequestHeaders();
   }

   public Headers getResponseHeaders() {
      return this.impl.getResponseHeaders();
   }

   public URI getRequestURI() {
      return this.impl.getRequestURI();
   }

   public String getRequestMethod() {
      return this.impl.getRequestMethod();
   }

   public HttpContextImpl getHttpContext() {
      return this.impl.getHttpContext();
   }

   public void close() {
      this.impl.close();
   }

   public InputStream getRequestBody() {
      return this.impl.getRequestBody();
   }

   public int getResponseCode() {
      return this.impl.getResponseCode();
   }

   public OutputStream getResponseBody() {
      return this.impl.getResponseBody();
   }

   public void sendResponseHeaders(int rCode, long contentLen) throws IOException {
      this.impl.sendResponseHeaders(rCode, contentLen);
   }

   public InetSocketAddress getRemoteAddress() {
      return this.impl.getRemoteAddress();
   }

   public InetSocketAddress getLocalAddress() {
      return this.impl.getLocalAddress();
   }

   public String getProtocol() {
      return this.impl.getProtocol();
   }

   public Object getAttribute(String name) {
      return this.impl.getAttribute(name);
   }

   public void setAttribute(String name, Object value) {
      this.impl.setAttribute(name, value);
   }

   public void setStreams(InputStream i, OutputStream o) {
      this.impl.setStreams(i, o);
   }

   public HttpPrincipal getPrincipal() {
      return this.impl.getPrincipal();
   }

   ExchangeImpl getExchangeImpl() {
      return this.impl;
   }
}
