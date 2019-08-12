package sun.net.httpserver;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class HttpsServerImpl extends HttpsServer {
   ServerImpl server;

   HttpsServerImpl() throws IOException {
      this(new InetSocketAddress(443), 0);
   }

   HttpsServerImpl(InetSocketAddress addr, int backlog) throws IOException {
      this.server = new ServerImpl(this, "https", addr, backlog);
   }

   public void setHttpsConfigurator(HttpsConfigurator config) {
      this.server.setHttpsConfigurator(config);
   }

   public HttpsConfigurator getHttpsConfigurator() {
      return this.server.getHttpsConfigurator();
   }

   public void bind(InetSocketAddress addr, int backlog) throws IOException {
      this.server.bind(addr, backlog);
   }

   public void start() {
      this.server.start();
   }

   public void setExecutor(Executor executor) {
      this.server.setExecutor(executor);
   }

   public Executor getExecutor() {
      return this.server.getExecutor();
   }

   public void stop(int delay) {
      this.server.stop(delay);
   }

   public HttpContextImpl createContext(String path, HttpHandler handler) {
      return this.server.createContext(path, handler);
   }

   public HttpContextImpl createContext(String path) {
      return this.server.createContext(path);
   }

   public void removeContext(String path) throws IllegalArgumentException {
      this.server.removeContext(path);
   }

   public void removeContext(HttpContext context) throws IllegalArgumentException {
      this.server.removeContext(context);
   }

   public InetSocketAddress getAddress() {
      return this.server.getAddress();
   }
}
