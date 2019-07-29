package be.ehealth.businessconnector.genericasync.domain;

public class TackQuery {
   private Integer max;

   private TackQuery(TackQuery.Builder builder) {
      this.max = builder.max;
   }

   public static TackQuery.Builder newBuilder() {
      return new TackQuery.Builder();
   }

   // $FF: synthetic method
   TackQuery(TackQuery.Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      private Integer max;

      private Builder() {
      }

      public TackQuery.Builder max(Integer max) {
         this.max = max;
         return this;
      }

      public TackQuery build() {
         return new TackQuery(this);
      }

      // $FF: synthetic method
      Builder(Object x0) {
         this();
      }
   }
}
