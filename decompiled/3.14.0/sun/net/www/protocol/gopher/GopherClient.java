package sun.net.www.protocol.gopher;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.AccessController;
import sun.net.NetworkClient;
import sun.net.www.MessageHeader;
import sun.net.www.URLConnection;
import sun.security.action.GetBooleanAction;
import sun.security.action.GetIntegerAction;
import sun.security.action.GetPropertyAction;

public class GopherClient extends NetworkClient implements Runnable {
   /** @deprecated */
   @Deprecated
   public static boolean useGopherProxy = ((Boolean)AccessController.doPrivileged(new GetBooleanAction("gopherProxySet"))).booleanValue();
   /** @deprecated */
   @Deprecated
   public static String gopherProxyHost = (String)AccessController.doPrivileged(new GetPropertyAction("gopherProxyHost"));
   /** @deprecated */
   @Deprecated
   public static int gopherProxyPort = ((Integer)AccessController.doPrivileged(new GetIntegerAction("gopherProxyPort", 80))).intValue();
   PipedOutputStream os;
   URL u;
   int gtype;
   String gkey;
   URLConnection connection;

   GopherClient(URLConnection connection) {
      this.connection = connection;
   }

   public static boolean getUseGopherProxy() {
      return ((Boolean)AccessController.doPrivileged(new GetBooleanAction("gopherProxySet"))).booleanValue();
   }

   public static String getGopherProxyHost() {
      String host = (String)AccessController.doPrivileged(new GetPropertyAction("gopherProxyHost"));
      if ("".equals(host)) {
         host = null;
      }

      return host;
   }

   public static int getGopherProxyPort() {
      return ((Integer)AccessController.doPrivileged(new GetIntegerAction("gopherProxyPort", 80))).intValue();
   }

   InputStream openStream(URL u) throws IOException {
      this.u = u;
      this.os = this.os;
      int i = 0;
      String s = u.getFile();
      int limit = s.length();

      char c;
      for(c = s.charAt(i); i < limit && c == '/'; ++i) {
         ;
      }

      this.gtype = c == '/' ? 49 : c;
      if (i < limit) {
         ++i;
      }

      this.gkey = s.substring(i);
      this.openServer(u.getHost(), u.getPort() <= 0 ? 70 : u.getPort());
      MessageHeader msgh = new MessageHeader();
      switch(this.gtype) {
      case 48:
      case 55:
         msgh.add("content-type", "text/plain");
         break;
      case 49:
         msgh.add("content-type", "text/html");
         break;
      case 73:
      case 103:
         msgh.add("content-type", "image/gif");
         break;
      default:
         msgh.add("content-type", "content/unknown");
      }

      i = this.gkey.indexOf(63);
      if (this.gtype != 55) {
         this.serverOutput.print(this.decodePercent(this.gkey) + "\r\n");
         this.serverOutput.flush();
      } else if (i >= 0) {
         this.serverOutput.print(this.decodePercent(this.gkey.substring(0, i) + "\t" + this.gkey.substring(i + 1) + "\r\n"));
         this.serverOutput.flush();
         msgh.add("content-type", "text/html");
      } else {
         msgh.add("content-type", "text/html");
      }

      this.connection.setProperties(msgh);
      if (msgh.findValue("content-type") == "text/html") {
         this.os = new PipedOutputStream();
         PipedInputStream ret = new PipedInputStream();
         ret.connect(this.os);
         (new Thread(this)).start();
         return ret;
      } else {
         return new GopherInputStream(this, this.serverInput);
      }
   }

   private String decodePercent(String s) {
      if (s != null && s.indexOf(37) >= 0) {
         int limit = s.length();
         char[] d = new char[limit];
         int dp = 0;

         for(int sp = 0; sp < limit; ++sp) {
            int c = s.charAt(sp);
            if (c == 37 && sp + 2 < limit) {
               int s1 = s.charAt(sp + 1);
               int s2 = s.charAt(sp + 2);
               int s1;
               if ('0' <= s1 && s1 <= '9') {
                  s1 = s1 - 48;
               } else if ('a' <= s1 && s1 <= 'f') {
                  s1 = s1 - 97 + 10;
               } else if ('A' <= s1 && s1 <= 'F') {
                  s1 = s1 - 65 + 10;
               } else {
                  s1 = -1;
               }

               int s2;
               if ('0' <= s2 && s2 <= '9') {
                  s2 = s2 - 48;
               } else if ('a' <= s2 && s2 <= 'f') {
                  s2 = s2 - 97 + 10;
               } else if ('A' <= s2 && s2 <= 'F') {
                  s2 = s2 - 65 + 10;
               } else {
                  s2 = -1;
               }

               if (s1 >= 0 && s2 >= 0) {
                  c = s1 << 4 | s2;
                  sp += 2;
               }
            }

            d[dp++] = (char)c;
         }

         return new String(d, 0, dp);
      } else {
         return s;
      }
   }

   private String encodePercent(String s) {
      if (s == null) {
         return s;
      } else {
         int limit = s.length();
         char[] d = null;
         int dp = 0;

         for(int sp = 0; sp < limit; ++sp) {
            int c = s.charAt(sp);
            char[] nd;
            if (c > ' ' && c != '"' && c != '%') {
               if (d != null) {
                  if (dp >= d.length) {
                     nd = new char[dp + 10];
                     System.arraycopy(d, 0, nd, 0, dp);
                     d = nd;
                  }

                  d[dp] = (char)c;
               }

               ++dp;
            } else {
               if (d == null) {
                  d = s.toCharArray();
               }

               if (dp + 3 >= d.length) {
                  nd = new char[dp + 10];
                  System.arraycopy(d, 0, nd, 0, dp);
                  d = nd;
               }

               d[dp] = '%';
               int dig = c >> 4 & 15;
               d[dp + 1] = (char)(dig < 10 ? 48 + dig : 55 + dig);
               dig = c & 15;
               d[dp + 2] = (char)(dig < 10 ? 48 + dig : 55 + dig);
               dp += 3;
            }
         }

         return d == null ? s : new String(d, 0, dp);
      }
   }

   public void run() {
      try {
         int qpos = this.gkey.indexOf(63);
         PrintStream ps;
         if (this.gtype == 55 && qpos < 0) {
            ps = new PrintStream(this.os, false, encoding);
            ps.print("<html><head><title>Searchable Gopher Index</title></head>\n<body><h1>Searchable Gopher Index</h1><isindex>\n</body></html>\n");
         } else if (this.gtype != 49 && this.gtype != 55) {
            byte[] buf = new byte[2048];

            int n;
            try {
               while((n = this.serverInput.read(buf)) >= 0) {
                  this.os.write(buf, 0, n);
               }
            } catch (Exception var23) {
               ;
            }
         } else {
            ps = new PrintStream(this.os, false, encoding);
            String title = null;
            if (this.gtype == 55) {
               title = "Results of searching for \"" + this.gkey.substring(qpos + 1) + "\" on " + this.u.getHost();
            } else {
               title = "Gopher directory " + this.gkey + " from " + this.u.getHost();
            }

            ps.print("<html><head><title>");
            ps.print(title);
            ps.print("</title></head>\n<body>\n<H1>");
            ps.print(title);
            ps.print("</h1><dl compact>\n");
            DataInputStream ds = new DataInputStream(this.serverInput);

            String s;
            while((s = ds.readLine()) != null) {
               int len;
               for(len = s.length(); len > 0 && s.charAt(len - 1) <= ' '; --len) {
                  ;
               }

               if (len > 0) {
                  int key = s.charAt(0);
                  int t1 = s.indexOf(9);
                  int t2 = t1 > 0 ? s.indexOf(9, t1 + 1) : -1;
                  int t3 = t2 > 0 ? s.indexOf(9, t2 + 1) : -1;
                  if (t3 >= 0) {
                     String port = t3 + 1 < len ? ":" + s.substring(t3 + 1, len) : "";
                     String host = t2 + 1 < t3 ? s.substring(t2 + 1, t3) : this.u.getHost();
                     ps.print("<dt><a href=\"gopher://" + host + port + "/" + s.substring(0, 1) + this.encodePercent(s.substring(t1 + 1, t2)) + "\">\n");
                     ps.print("<img align=middle border=0 width=25 height=32 src=");
                     switch(key) {
                     case '0':
                        ps.print(System.getProperty("java.net.ftp.imagepath.text"));
                        break;
                     case '1':
                        ps.print(System.getProperty("java.net.ftp.imagepath.directory"));
                        break;
                     case 'g':
                        ps.print(System.getProperty("java.net.ftp.imagepath.gif"));
                        break;
                     default:
                        ps.print(System.getProperty("java.net.ftp.imagepath.file"));
                     }

                     ps.print(".gif align=middle><dd>\n");
                     ps.print(s.substring(1, t1) + "</a>\n");
                  }
               }
            }

            ps.print("</dl></body>\n");
            ps.close();
         }
      } catch (UnsupportedEncodingException var24) {
         throw new InternalError(encoding + " encoding not found");
      } catch (IOException var25) {
         ;
      } finally {
         try {
            this.closeServer();
            this.os.close();
         } catch (IOException var22) {
            ;
         }

      }

   }
}
