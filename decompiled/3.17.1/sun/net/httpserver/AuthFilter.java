package sun.net.httpserver;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Authenticator.Failure;
import com.sun.net.httpserver.Authenticator.Result;
import com.sun.net.httpserver.Authenticator.Retry;
import com.sun.net.httpserver.Authenticator.Success;
import com.sun.net.httpserver.Filter.Chain;
import java.io.IOException;
import java.io.InputStream;

public class AuthFilter extends Filter {
   private Authenticator authenticator;

   public AuthFilter(Authenticator authenticator) {
      this.authenticator = authenticator;
   }

   public String description() {
      return "Authentication filter";
   }

   public void setAuthenticator(Authenticator a) {
      this.authenticator = a;
   }

   public void consumeInput(HttpExchange t) throws IOException {
      InputStream i = t.getRequestBody();
      byte[] b = new byte[4096];

      while(i.read(b) != -1) {
      }

      i.close();
   }

   public void doFilter(HttpExchange t, Chain chain) throws IOException {
      if (this.authenticator != null) {
         Result r = this.authenticator.authenticate(t);
         if (r instanceof Success) {
            Success s = (Success)r;
            ExchangeImpl e = ExchangeImpl.get(t);
            e.setPrincipal(s.getPrincipal());
            chain.doFilter(t);
         } else if (r instanceof Retry) {
            Retry ry = (Retry)r;
            this.consumeInput(t);
            t.sendResponseHeaders(ry.getResponseCode(), -1L);
         } else if (r instanceof Failure) {
            Failure f = (Failure)r;
            this.consumeInput(t);
            t.sendResponseHeaders(f.getResponseCode(), -1L);
         }
      } else {
         chain.doFilter(t);
      }

   }
}
