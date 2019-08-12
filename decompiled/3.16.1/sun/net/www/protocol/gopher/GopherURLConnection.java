package sun.net.www.protocol.gopher;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketPermission;
import java.net.URL;
import java.security.Permission;
import sun.net.www.URLConnection;

class GopherURLConnection extends URLConnection {
   Permission permission;

   GopherURLConnection(URL u) {
      super(u);
   }

   public void connect() throws IOException {
   }

   public InputStream getInputStream() throws IOException {
      return (new GopherClient(this)).openStream(this.url);
   }

   public Permission getPermission() {
      if (this.permission == null) {
         int port = this.url.getPort();
         port = port < 0 ? 70 : port;
         String host = this.url.getHost() + ":" + this.url.getPort();
         this.permission = new SocketPermission(host, "connect");
      }

      return this.permission;
   }
}
