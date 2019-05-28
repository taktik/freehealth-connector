package sun.net.httpserver;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.Filter.Chain;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

class ServerImpl implements TimeSource {
   private String protocol;
   private boolean https;
   private Executor executor;
   private HttpsConfigurator httpsConfig;
   private SSLContext sslContext;
   private ContextList contexts;
   private InetSocketAddress address;
   private ServerSocketChannel schan;
   private Selector selector;
   private SelectionKey listenerKey;
   private Set<HttpConnection> idleConnections;
   private Set<HttpConnection> allConnections;
   private Set<HttpConnection> reqConnections;
   private Set<HttpConnection> rspConnections;
   private List<Event> events;
   private Object lolock = new Object();
   private volatile boolean finished = false;
   private volatile boolean terminating = false;
   private boolean bound = false;
   private boolean started = false;
   private volatile long time;
   private volatile long ticks;
   private HttpServer wrapper;
   static final int CLOCK_TICK = ServerConfig.getClockTick();
   static final long IDLE_INTERVAL = ServerConfig.getIdleInterval();
   static final int MAX_IDLE_CONNECTIONS = ServerConfig.getMaxIdleConnections();
   static final long TIMER_MILLIS = ServerConfig.getTimerMillis();
   static final long MAX_REQ_TIME = getTimeMillis(ServerConfig.getMaxReqTime());
   static final long MAX_RSP_TIME = getTimeMillis(ServerConfig.getMaxRspTime());
   static final boolean timer1Enabled;
   private Timer timer;
   private Timer timer1;
   private Logger logger;
   ServerImpl.Dispatcher dispatcher;
   static boolean debug;
   private int exchangeCount = 0;

   ServerImpl(HttpServer wrapper, String protocol, InetSocketAddress addr, int backlog) throws IOException {
      this.protocol = protocol;
      this.wrapper = wrapper;
      this.logger = Logger.getLogger("com.sun.net.httpserver");
      ServerConfig.checkLegacyProperties(this.logger);
      this.https = protocol.equalsIgnoreCase("https");
      this.address = addr;
      this.contexts = new ContextList();
      this.schan = ServerSocketChannel.open();
      if (addr != null) {
         ServerSocket socket = this.schan.socket();
         socket.bind(addr, backlog);
         this.bound = true;
      }

      this.selector = Selector.open();
      this.schan.configureBlocking(false);
      this.listenerKey = this.schan.register(this.selector, 16);
      this.dispatcher = new ServerImpl.Dispatcher();
      this.idleConnections = Collections.synchronizedSet(new HashSet());
      this.allConnections = Collections.synchronizedSet(new HashSet());
      this.reqConnections = Collections.synchronizedSet(new HashSet());
      this.rspConnections = Collections.synchronizedSet(new HashSet());
      this.time = System.currentTimeMillis();
      this.timer = new Timer("server-timer", true);
      this.timer.schedule(new ServerImpl.ServerTimerTask(), (long)CLOCK_TICK, (long)CLOCK_TICK);
      if (timer1Enabled) {
         this.timer1 = new Timer("server-timer1", true);
         this.timer1.schedule(new ServerImpl.ServerTimerTask1(), TIMER_MILLIS, TIMER_MILLIS);
         this.logger.config("HttpServer timer1 enabled period in ms:  " + TIMER_MILLIS);
         this.logger.config("MAX_REQ_TIME:  " + MAX_REQ_TIME);
         this.logger.config("MAX_RSP_TIME:  " + MAX_RSP_TIME);
      }

      this.events = new LinkedList();
      this.logger.config("HttpServer created " + protocol + " " + addr);
   }

   public void bind(InetSocketAddress addr, int backlog) throws IOException {
      if (this.bound) {
         throw new BindException("HttpServer already bound");
      } else if (addr == null) {
         throw new NullPointerException("null address");
      } else {
         ServerSocket socket = this.schan.socket();
         socket.bind(addr, backlog);
         this.bound = true;
      }
   }

   public void start() {
      if (this.bound && !this.started && !this.finished) {
         if (this.executor == null) {
            this.executor = new ServerImpl.DefaultExecutor();
         }

         Thread t = new Thread(this.dispatcher);
         this.started = true;
         t.start();
      } else {
         throw new IllegalStateException("server in wrong state");
      }
   }

   public void setExecutor(Executor executor) {
      if (this.started) {
         throw new IllegalStateException("server already started");
      } else {
         this.executor = executor;
      }
   }

   public Executor getExecutor() {
      return this.executor;
   }

   public void setHttpsConfigurator(HttpsConfigurator config) {
      if (config == null) {
         throw new NullPointerException("null HttpsConfigurator");
      } else if (this.started) {
         throw new IllegalStateException("server already started");
      } else {
         this.httpsConfig = config;
         this.sslContext = config.getSSLContext();
      }
   }

   public HttpsConfigurator getHttpsConfigurator() {
      return this.httpsConfig;
   }

   public void stop(int delay) {
      if (delay < 0) {
         throw new IllegalArgumentException("negative delay parameter");
      } else {
         this.terminating = true;

         try {
            this.schan.close();
         } catch (IOException var8) {
         }

         this.selector.wakeup();
         long latest = System.currentTimeMillis() + (long)(delay * 1000);

         while(System.currentTimeMillis() < latest) {
            this.delay();
            if (this.finished) {
               break;
            }
         }

         this.finished = true;
         this.selector.wakeup();
         synchronized(this.allConnections) {
            Iterator i$ = this.allConnections.iterator();

            while(true) {
               if (!i$.hasNext()) {
                  break;
               }

               HttpConnection c = (HttpConnection)i$.next();
               c.close();
            }
         }

         this.allConnections.clear();
         this.idleConnections.clear();
         this.timer.cancel();
         if (timer1Enabled) {
            this.timer1.cancel();
         }

      }
   }

   public synchronized HttpContextImpl createContext(String path, HttpHandler handler) {
      if (handler != null && path != null) {
         HttpContextImpl context = new HttpContextImpl(this.protocol, path, handler, this);
         this.contexts.add(context);
         this.logger.config("context created: " + path);
         return context;
      } else {
         throw new NullPointerException("null handler, or path parameter");
      }
   }

   public synchronized HttpContextImpl createContext(String path) {
      if (path == null) {
         throw new NullPointerException("null path parameter");
      } else {
         HttpContextImpl context = new HttpContextImpl(this.protocol, path, (HttpHandler)null, this);
         this.contexts.add(context);
         this.logger.config("context created: " + path);
         return context;
      }
   }

   public synchronized void removeContext(String path) throws IllegalArgumentException {
      if (path == null) {
         throw new NullPointerException("null path parameter");
      } else {
         this.contexts.remove(this.protocol, path);
         this.logger.config("context removed: " + path);
      }
   }

   public synchronized void removeContext(HttpContext context) throws IllegalArgumentException {
      if (!(context instanceof HttpContextImpl)) {
         throw new IllegalArgumentException("wrong HttpContext type");
      } else {
         this.contexts.remove((HttpContextImpl)context);
         this.logger.config("context removed: " + context.getPath());
      }
   }

   public InetSocketAddress getAddress() {
      return (InetSocketAddress)this.schan.socket().getLocalSocketAddress();
   }

   Selector getSelector() {
      return this.selector;
   }

   void addEvent(Event r) {
      synchronized(this.lolock) {
         this.events.add(r);
         this.selector.wakeup();
      }
   }

   static synchronized void dprint(String s) {
      if (debug) {
         System.out.println(s);
      }

   }

   static synchronized void dprint(Exception e) {
      if (debug) {
         System.out.println(e);
         e.printStackTrace();
      }

   }

   Logger getLogger() {
      return this.logger;
   }

   private void closeConnection(HttpConnection conn) {
      conn.close();
      this.allConnections.remove(conn);
      switch(conn.getState()) {
      case REQUEST:
         this.reqConnections.remove(conn);
         break;
      case RESPONSE:
         this.rspConnections.remove(conn);
         break;
      case IDLE:
         this.idleConnections.remove(conn);
      }

      assert !this.reqConnections.remove(conn);

      assert !this.rspConnections.remove(conn);

      assert !this.idleConnections.remove(conn);

   }

   void logReply(int code, String requestStr, String text) {
      if (this.logger.isLoggable(Level.FINE)) {
         if (text == null) {
            text = "";
         }

         String r;
         if (requestStr.length() > 80) {
            r = requestStr.substring(0, 80) + "<TRUNCATED>";
         } else {
            r = requestStr;
         }

         String message = r + " [" + code + " " + Code.msg(code) + "] (" + text + ")";
         this.logger.fine(message);
      }
   }

   long getTicks() {
      return this.ticks;
   }

   public long getTime() {
      return this.time;
   }

   void delay() {
      Thread.yield();

      try {
         Thread.sleep(200L);
      } catch (InterruptedException var2) {
      }

   }

   synchronized void startExchange() {
      ++this.exchangeCount;
   }

   synchronized int endExchange() {
      --this.exchangeCount;

      assert this.exchangeCount >= 0;

      return this.exchangeCount;
   }

   HttpServer getWrapper() {
      return this.wrapper;
   }

   void requestStarted(HttpConnection c) {
      c.creationTime = this.getTime();
      c.setState(HttpConnection.State.REQUEST);
      this.reqConnections.add(c);
   }

   void requestCompleted(HttpConnection c) {
      assert c.getState() == HttpConnection.State.REQUEST;

      this.reqConnections.remove(c);
      c.rspStartedTime = this.getTime();
      this.rspConnections.add(c);
      c.setState(HttpConnection.State.RESPONSE);
   }

   void responseCompleted(HttpConnection c) {
      assert c.getState() == HttpConnection.State.RESPONSE;

      this.rspConnections.remove(c);
      c.setState(HttpConnection.State.IDLE);
   }

   void logStackTrace(String s) {
      this.logger.finest(s);
      StringBuilder b = new StringBuilder();
      StackTraceElement[] e = Thread.currentThread().getStackTrace();

      for(int i = 0; i < e.length; ++i) {
         b.append(e[i].toString()).append("\n");
      }

      this.logger.finest(b.toString());
   }

   static long getTimeMillis(long secs) {
      return secs == -1L ? -1L : secs * 1000L;
   }

   static {
      timer1Enabled = MAX_REQ_TIME != -1L || MAX_RSP_TIME != -1L;
      debug = ServerConfig.debugEnabled();
   }

   class ServerTimerTask1 extends TimerTask {
      public void run() {
         LinkedList<HttpConnection> toClose = new LinkedList();
         ServerImpl.this.time = System.currentTimeMillis();
         Iterator i$;
         HttpConnection c;
         synchronized(ServerImpl.this.reqConnections) {
            if (ServerImpl.MAX_REQ_TIME != -1L) {
               i$ = ServerImpl.this.reqConnections.iterator();

               while(i$.hasNext()) {
                  c = (HttpConnection)i$.next();
                  if (c.creationTime + ServerImpl.TIMER_MILLIS + ServerImpl.MAX_REQ_TIME <= ServerImpl.this.time) {
                     toClose.add(c);
                  }
               }

               i$ = toClose.iterator();

               while(i$.hasNext()) {
                  c = (HttpConnection)i$.next();
                  ServerImpl.this.logger.log(Level.FINE, "closing: no request: " + c);
                  ServerImpl.this.reqConnections.remove(c);
                  ServerImpl.this.allConnections.remove(c);
                  c.close();
               }
            }
         }

         toClose = new LinkedList();
         synchronized(ServerImpl.this.rspConnections) {
            if (ServerImpl.MAX_RSP_TIME != -1L) {
               i$ = ServerImpl.this.rspConnections.iterator();

               while(i$.hasNext()) {
                  c = (HttpConnection)i$.next();
                  if (c.rspStartedTime + ServerImpl.TIMER_MILLIS + ServerImpl.MAX_RSP_TIME <= ServerImpl.this.time) {
                     toClose.add(c);
                  }
               }

               i$ = toClose.iterator();

               while(i$.hasNext()) {
                  c = (HttpConnection)i$.next();
                  ServerImpl.this.logger.log(Level.FINE, "closing: no response: " + c);
                  ServerImpl.this.rspConnections.remove(c);
                  ServerImpl.this.allConnections.remove(c);
                  c.close();
               }
            }

         }
      }
   }

   class ServerTimerTask extends TimerTask {
      public void run() {
         LinkedList<HttpConnection> toClose = new LinkedList();
         ServerImpl.this.time = System.currentTimeMillis();
         ServerImpl.this.ticks++;
         synchronized(ServerImpl.this.idleConnections) {
            Iterator i$ = ServerImpl.this.idleConnections.iterator();

            HttpConnection c;
            while(i$.hasNext()) {
               c = (HttpConnection)i$.next();
               if (c.time <= ServerImpl.this.time) {
                  toClose.add(c);
               }
            }

            i$ = toClose.iterator();

            while(i$.hasNext()) {
               c = (HttpConnection)i$.next();
               ServerImpl.this.idleConnections.remove(c);
               ServerImpl.this.allConnections.remove(c);
               c.close();
            }

         }
      }
   }

   class Exchange implements Runnable {
      SocketChannel chan;
      HttpConnection connection;
      HttpContextImpl context;
      InputStream rawin;
      OutputStream rawout;
      String protocol;
      ExchangeImpl tx;
      HttpContextImpl ctx;
      boolean rejected = false;

      Exchange(SocketChannel chan, String protocol, HttpConnection conn) throws IOException {
         this.chan = chan;
         this.connection = conn;
         this.protocol = protocol;
      }

      public void run() {
         this.context = this.connection.getHttpContext();
         SSLEngine engine = null;
         String requestLine = null;
         SSLStreams sslStreams = null;

         try {
            boolean newconnection;
            if (this.context != null) {
               this.rawin = this.connection.getInputStream();
               this.rawout = this.connection.getRawOutputStream();
               newconnection = false;
            } else {
               newconnection = true;
               if (ServerImpl.this.https) {
                  if (ServerImpl.this.sslContext == null) {
                     ServerImpl.this.logger.warning("SSL connection received. No https contxt created");
                     throw new HttpError("No SSL context established");
                  }

                  sslStreams = new SSLStreams(ServerImpl.this, ServerImpl.this.sslContext, this.chan);
                  this.rawin = sslStreams.getInputStream();
                  this.rawout = sslStreams.getOutputStream();
                  engine = sslStreams.getSSLEngine();
                  this.connection.sslStreams = sslStreams;
               } else {
                  this.rawin = new BufferedInputStream(new Request.ReadStream(ServerImpl.this, this.chan));
                  this.rawout = new Request.WriteStream(ServerImpl.this, this.chan);
               }

               this.connection.raw = this.rawin;
               this.connection.rawout = this.rawout;
            }

            Request req = new Request(this.rawin, this.rawout);
            requestLine = req.requestLine();
            if (requestLine == null) {
               ServerImpl.this.closeConnection(this.connection);
               return;
            }

            int space = requestLine.indexOf(32);
            if (space == -1) {
               this.reject(400, requestLine, "Bad request line");
               return;
            }

            String method = requestLine.substring(0, space);
            int start = space + 1;
            space = requestLine.indexOf(32, start);
            if (space == -1) {
               this.reject(400, requestLine, "Bad request line");
               return;
            }

            String uriStr = requestLine.substring(start, space);
            URI uri = new URI(uriStr);
            start = space + 1;
            String version = requestLine.substring(start);
            Headers headers = req.headers();
            String s = headers.getFirst("Transfer-encoding");
            long clen = 0L;
            if (s != null && s.equalsIgnoreCase("chunked")) {
               clen = -1L;
            } else {
               s = headers.getFirst("Content-Length");
               if (s != null) {
                  clen = Long.parseLong(s);
               }

               if (clen == 0L) {
                  ServerImpl.this.requestCompleted(this.connection);
               }
            }

            this.ctx = ServerImpl.this.contexts.findContext(this.protocol, uri.getPath());
            if (this.ctx == null) {
               this.reject(404, requestLine, "No context found for request");
               return;
            }

            this.connection.setContext(this.ctx);
            if (this.ctx.getHandler() == null) {
               this.reject(500, requestLine, "No handler for context");
               return;
            }

            this.tx = new ExchangeImpl(method, uri, req, clen, this.connection);
            String chdr = headers.getFirst("Connection");
            Headers rheaders = this.tx.getResponseHeaders();
            if (chdr != null && chdr.equalsIgnoreCase("close")) {
               this.tx.close = true;
            }

            if (version.equalsIgnoreCase("http/1.0")) {
               this.tx.http10 = true;
               if (chdr == null) {
                  this.tx.close = true;
                  rheaders.set("Connection", "close");
               } else if (chdr.equalsIgnoreCase("keep-alive")) {
                  rheaders.set("Connection", "keep-alive");
                  int idle = (int)ServerConfig.getIdleInterval() / 1000;
                  int max = ServerConfig.getMaxIdleConnections();
                  String val = "timeout=" + idle + ", max=" + max;
                  rheaders.set("Keep-Alive", val);
               }
            }

            if (newconnection) {
               this.connection.setParameters(this.rawin, this.rawout, this.chan, engine, sslStreams, ServerImpl.this.sslContext, this.protocol, this.ctx, this.rawin);
            }

            String exp = headers.getFirst("Expect");
            if (exp != null && exp.equalsIgnoreCase("100-continue")) {
               ServerImpl.this.logReply(100, requestLine, (String)null);
               this.sendReply(100, false, (String)null);
            }

            List<Filter> sf = this.ctx.getSystemFilters();
            List<Filter> uf = this.ctx.getFilters();
            Chain sc = new Chain(sf, this.ctx.getHandler());
            Chain uc = new Chain(uf, new ServerImpl.Exchange.LinkHandler(sc));
            this.tx.getRequestBody();
            this.tx.getResponseBody();
            if (ServerImpl.this.https) {
               uc.doFilter(new HttpsExchangeImpl(this.tx));
            } else {
               uc.doFilter(new HttpExchangeImpl(this.tx));
            }
         } catch (IOException var23) {
            ServerImpl.this.logger.log(Level.FINER, "ServerImpl.Exchange (1)", var23);
            ServerImpl.this.closeConnection(this.connection);
         } catch (NumberFormatException var24) {
            this.reject(400, requestLine, "NumberFormatException thrown");
         } catch (URISyntaxException var25) {
            this.reject(400, requestLine, "URISyntaxException thrown");
         } catch (Exception var26) {
            ServerImpl.this.logger.log(Level.FINER, "ServerImpl.Exchange (2)", var26);
            ServerImpl.this.closeConnection(this.connection);
         }

      }

      void reject(int code, String requestStr, String message) {
         this.rejected = true;
         ServerImpl.this.logReply(code, requestStr, message);
         this.sendReply(code, false, "<h1>" + code + Code.msg(code) + "</h1>" + message);
         ServerImpl.this.closeConnection(this.connection);
      }

      void sendReply(int code, boolean closeNow, String text) {
         try {
            StringBuilder builder = new StringBuilder(512);
            builder.append("HTTP/1.1 ").append(code).append(Code.msg(code)).append("\r\n");
            if (text != null && text.length() != 0) {
               builder.append("Content-Length: ").append(text.length()).append("\r\n").append("Content-Type: text/html\r\n");
            } else {
               builder.append("Content-Length: 0\r\n");
               text = "";
            }

            if (closeNow) {
               builder.append("Connection: close\r\n");
            }

            builder.append("\r\n").append(text);
            String s = builder.toString();
            byte[] b = s.getBytes("ISO8859_1");
            this.rawout.write(b);
            this.rawout.flush();
            if (closeNow) {
               ServerImpl.this.closeConnection(this.connection);
            }
         } catch (IOException var7) {
            ServerImpl.this.logger.log(Level.FINER, "ServerImpl.sendReply", var7);
            ServerImpl.this.closeConnection(this.connection);
         }

      }

      class LinkHandler implements HttpHandler {
         Chain nextChain;

         LinkHandler(Chain nextChain) {
            this.nextChain = nextChain;
         }

         public void handle(HttpExchange exchange) throws IOException {
            this.nextChain.doFilter(exchange);
         }
      }
   }

   class Dispatcher implements Runnable {
      final List<HttpConnection> connsToRegister = new LinkedList();

      private void handleEvent(Event r) {
         ExchangeImpl t = r.exchange;
         HttpConnection c = t.getConnection();

         try {
            if (r instanceof WriteFinishedEvent) {
               int exchanges = ServerImpl.this.endExchange();
               if (ServerImpl.this.terminating && exchanges == 0) {
                  ServerImpl.this.finished = true;
               }

               ServerImpl.this.responseCompleted(c);
               LeftOverInputStream is = t.getOriginalInputStream();
               if (!is.isEOF()) {
                  t.close = true;
               }

               if (!t.close && ServerImpl.this.idleConnections.size() < ServerImpl.MAX_IDLE_CONNECTIONS) {
                  if (is.isDataBuffered()) {
                     ServerImpl.this.requestStarted(c);
                     this.handle(c.getChannel(), c);
                  } else {
                     this.connsToRegister.add(c);
                  }
               } else {
                  c.close();
                  ServerImpl.this.allConnections.remove(c);
               }
            }
         } catch (IOException var6) {
            ServerImpl.this.logger.log(Level.FINER, "Dispatcher (1)", var6);
            c.close();
         }

      }

      void reRegister(HttpConnection c) {
         try {
            SocketChannel chan = c.getChannel();
            chan.configureBlocking(false);
            SelectionKey key = chan.register(ServerImpl.this.selector, 1);
            key.attach(c);
            c.selectionKey = key;
            c.time = ServerImpl.this.getTime() + ServerImpl.IDLE_INTERVAL;
            ServerImpl.this.idleConnections.add(c);
         } catch (IOException var4) {
            ServerImpl.dprint((Exception)var4);
            ServerImpl.this.logger.log(Level.FINER, "Dispatcher(8)", var4);
            c.close();
         }

      }

      public void run() {
         while(!ServerImpl.this.finished) {
            try {
               Iterator i$ = this.connsToRegister.iterator();

               while(i$.hasNext()) {
                  HttpConnection c = (HttpConnection)i$.next();
                  this.reRegister(c);
               }

               this.connsToRegister.clear();
               List<Event> list = null;
               ServerImpl.this.selector.select(1000L);
               synchronized(ServerImpl.this.lolock) {
                  if (ServerImpl.this.events.size() > 0) {
                     list = ServerImpl.this.events;
                     ServerImpl.this.events = new LinkedList();
                  }
               }

               if (list != null) {
                  Iterator i$x = list.iterator();

                  while(i$x.hasNext()) {
                     Event r = (Event)i$x.next();
                     this.handleEvent(r);
                  }
               }

               Set<SelectionKey> selected = ServerImpl.this.selector.selectedKeys();
               Iterator iter = selected.iterator();

               while(iter.hasNext()) {
                  SelectionKey key = (SelectionKey)iter.next();
                  iter.remove();
                  SocketChannel chan;
                  if (key.equals(ServerImpl.this.listenerKey)) {
                     if (!ServerImpl.this.terminating) {
                        chan = ServerImpl.this.schan.accept();
                        if (ServerConfig.noDelay()) {
                           chan.socket().setTcpNoDelay(true);
                        }

                        if (chan != null) {
                           chan.configureBlocking(false);
                           SelectionKey newkey = chan.register(ServerImpl.this.selector, 1);
                           HttpConnection cx = new HttpConnection();
                           cx.selectionKey = newkey;
                           cx.setChannel(chan);
                           newkey.attach(cx);
                           ServerImpl.this.requestStarted(cx);
                           ServerImpl.this.allConnections.add(cx);
                        }
                     }
                  } else {
                     try {
                        if (key.isReadable()) {
                           chan = (SocketChannel)key.channel();
                           HttpConnection conn = (HttpConnection)key.attachment();
                           key.cancel();
                           chan.configureBlocking(true);
                           if (ServerImpl.this.idleConnections.remove(conn)) {
                              ServerImpl.this.requestStarted(conn);
                           }

                           this.handle(chan, conn);
                        } else {
                           assert false;
                        }
                     } catch (CancelledKeyException var8) {
                        this.handleException(key, (Exception)null);
                     } catch (IOException var9) {
                        this.handleException(key, var9);
                     }
                  }
               }

               ServerImpl.this.selector.selectNow();
            } catch (IOException var11) {
               ServerImpl.this.logger.log(Level.FINER, "Dispatcher (4)", var11);
            } catch (Exception var12) {
               var12.printStackTrace();
               ServerImpl.this.logger.log(Level.FINER, "Dispatcher (7)", var12);
            }
         }

      }

      private void handleException(SelectionKey key, Exception e) {
         HttpConnection conn = (HttpConnection)key.attachment();
         if (e != null) {
            ServerImpl.this.logger.log(Level.FINER, "Dispatcher (2)", e);
         }

         ServerImpl.this.closeConnection(conn);
      }

      public void handle(SocketChannel chan, HttpConnection conn) throws IOException {
         try {
            ServerImpl.Exchange t = ServerImpl.this.new Exchange(chan, ServerImpl.this.protocol, conn);
            ServerImpl.this.executor.execute(t);
         } catch (HttpError var4) {
            ServerImpl.this.logger.log(Level.FINER, "Dispatcher (4)", var4);
            ServerImpl.this.closeConnection(conn);
         } catch (IOException var5) {
            ServerImpl.this.logger.log(Level.FINER, "Dispatcher (5)", var5);
            ServerImpl.this.closeConnection(conn);
         }

      }
   }

   private static class DefaultExecutor implements Executor {
      private DefaultExecutor() {
      }

      public void execute(Runnable task) {
         task.run();
      }

      // $FF: synthetic method
      DefaultExecutor(Object x0) {
         this();
      }
   }
}
