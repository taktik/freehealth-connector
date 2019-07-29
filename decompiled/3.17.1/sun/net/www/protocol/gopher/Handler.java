package sun.net.www.protocol.gopher;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.Proxy.Type;
import sun.net.www.protocol.http.HttpURLConnection;

public class Handler extends URLStreamHandler {
   protected int getDefaultPort() {
      return 70;
   }

   public URLConnection openConnection(URL u) throws IOException {
      return this.openConnection(u, (Proxy)null);
   }

   public URLConnection openConnection(URL u, Proxy p) throws IOException {
      if (p == null && GopherClient.getUseGopherProxy()) {
         String host = GopherClient.getGopherProxyHost();
         if (host != null) {
            InetSocketAddress saddr = InetSocketAddress.createUnresolved(host, GopherClient.getGopherProxyPort());
            p = new Proxy(Type.HTTP, saddr);
         }
      }

      return (URLConnection)(p != null ? new HttpURLConnection(u, p) : new GopherURLConnection(u));
   }
}
