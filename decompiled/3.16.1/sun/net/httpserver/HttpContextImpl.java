package sun.net.httpserver;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

class HttpContextImpl extends HttpContext {
   private String path;
   private String protocol;
   private HttpHandler handler;
   private Map<String, Object> attributes = new HashMap();
   private ServerImpl server;
   private List<Filter> sfilters = new LinkedList();
   private List<Filter> ufilters = new LinkedList();
   private Authenticator authenticator;
   private AuthFilter authfilter;

   HttpContextImpl(String protocol, String path, HttpHandler cb, ServerImpl server) {
      if (path != null && protocol != null && path.length() >= 1 && path.charAt(0) == '/') {
         this.protocol = protocol.toLowerCase();
         this.path = path;
         if (!this.protocol.equals("http") && !this.protocol.equals("https")) {
            throw new IllegalArgumentException("Illegal value for protocol");
         } else {
            this.handler = cb;
            this.server = server;
            this.authfilter = new AuthFilter((Authenticator)null);
            this.sfilters.add(this.authfilter);
         }
      } else {
         throw new IllegalArgumentException("Illegal value for path or protocol");
      }
   }

   public HttpHandler getHandler() {
      return this.handler;
   }

   public void setHandler(HttpHandler h) {
      if (h == null) {
         throw new NullPointerException("Null handler parameter");
      } else if (this.handler != null) {
         throw new IllegalArgumentException("handler already set");
      } else {
         this.handler = h;
      }
   }

   public String getPath() {
      return this.path;
   }

   public HttpServer getServer() {
      return this.server.getWrapper();
   }

   ServerImpl getServerImpl() {
      return this.server;
   }

   public String getProtocol() {
      return this.protocol;
   }

   public Map<String, Object> getAttributes() {
      return this.attributes;
   }

   public List<Filter> getFilters() {
      return this.ufilters;
   }

   List<Filter> getSystemFilters() {
      return this.sfilters;
   }

   public Authenticator setAuthenticator(Authenticator auth) {
      Authenticator old = this.authenticator;
      this.authenticator = auth;
      this.authfilter.setAuthenticator(auth);
      return old;
   }

   public Authenticator getAuthenticator() {
      return this.authenticator;
   }

   Logger getLogger() {
      return this.server.getLogger();
   }
}
