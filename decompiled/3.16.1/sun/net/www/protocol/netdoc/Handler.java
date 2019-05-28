package sun.net.www.protocol.netdoc;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.AccessController;
import sun.security.action.GetBooleanAction;
import sun.security.action.GetPropertyAction;

public class Handler extends URLStreamHandler {
   static URL base;

   public synchronized URLConnection openConnection(URL u) throws IOException {
      URLConnection uc = null;
      Boolean tmp = (Boolean)AccessController.doPrivileged(new GetBooleanAction("newdoc.localonly"));
      boolean localonly = tmp;
      String docurl = (String)AccessController.doPrivileged(new GetPropertyAction("doc.url"));
      String file = u.getFile();
      URL ru;
      if (!localonly) {
         try {
            if (base == null) {
               base = new URL(docurl);
            }

            ru = new URL(base, file);
         } catch (MalformedURLException var11) {
            ru = null;
         }

         if (ru != null) {
            uc = ru.openConnection();
         }
      }

      if (uc == null) {
         try {
            ru = new URL("file", "~", file);
            uc = ru.openConnection();
            InputStream var8 = uc.getInputStream();
         } catch (MalformedURLException var9) {
            uc = null;
         } catch (IOException var10) {
            uc = null;
         }
      }

      if (uc == null) {
         throw new IOException("Can't find file for URL: " + u.toExternalForm());
      } else {
         return uc;
      }
   }
}
