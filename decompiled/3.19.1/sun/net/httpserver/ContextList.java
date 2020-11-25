package sun.net.httpserver;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class ContextList {
   static final int MAX_CONTEXTS = 50;
   List<HttpContextImpl> list = new LinkedList();

   public synchronized void add(HttpContextImpl ctx) {
      assert ctx.getPath() != null;

      this.list.add(ctx);
   }

   public synchronized int size() {
      return this.list.size();
   }

   synchronized HttpContextImpl findContext(String protocol, String path) {
      return this.findContext(protocol, path, false);
   }

   synchronized HttpContextImpl findContext(String protocol, String path, boolean exact) {
      protocol = protocol.toLowerCase();
      String longest = "";
      HttpContextImpl lc = null;
      Iterator i$ = this.list.iterator();

      while(true) {
         HttpContextImpl ctx;
         String cpath;
         do {
            do {
               do {
                  if (!i$.hasNext()) {
                     return lc;
                  }

                  ctx = (HttpContextImpl)i$.next();
               } while(!ctx.getProtocol().equals(protocol));

               cpath = ctx.getPath();
            } while(exact && !cpath.equals(path));
         } while(!exact && !path.startsWith(cpath));

         if (cpath.length() > longest.length()) {
            longest = cpath;
            lc = ctx;
         }
      }
   }

   public synchronized void remove(String protocol, String path) throws IllegalArgumentException {
      HttpContextImpl ctx = this.findContext(protocol, path, true);
      if (ctx == null) {
         throw new IllegalArgumentException("cannot remove element from list");
      } else {
         this.list.remove(ctx);
      }
   }

   public synchronized void remove(HttpContextImpl context) throws IllegalArgumentException {
      Iterator i$ = this.list.iterator();

      HttpContextImpl ctx;
      do {
         if (!i$.hasNext()) {
            throw new IllegalArgumentException("no such context in list");
         }

         ctx = (HttpContextImpl)i$.next();
      } while(!ctx.equals(context));

      this.list.remove(ctx);
   }
}
