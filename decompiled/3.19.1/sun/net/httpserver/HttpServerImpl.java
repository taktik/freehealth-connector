package sun.net.httpserver;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class HttpServerImpl extends HttpServer {
   ServerImpl server;

   HttpServerImpl() throws IOException {
      this(new InetSocketAddress(80), 0);
   }

   HttpServerImpl(InetSocketAddress addr, int backlog) throws IOException {
      this.server = new ServerImpl(this, "http", addr, backlog);
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
