package sun.net.httpserver;

class HttpError extends RuntimeException {
   private static final long serialVersionUID = 8769596371344178179L;

   public HttpError(String msg) {
      super(msg);
   }
}
