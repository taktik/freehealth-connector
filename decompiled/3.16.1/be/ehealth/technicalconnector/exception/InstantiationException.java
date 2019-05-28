package be.ehealth.technicalconnector.exception;

public class InstantiationException extends RuntimeException {
   private static final long serialVersionUID = 1454838527369367045L;

   public InstantiationException(String msg, Exception e) {
      super(msg, e);
   }

   public InstantiationException(Exception e) {
      super(e);
   }
}
