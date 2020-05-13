package org.taktik.connector.business.genericasync.domain;

public class TackQuery {
   private Integer max;

   private TackQuery(Builder builder) {
      this.max = builder.max;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   // $FF: synthetic method
   TackQuery(Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      private Integer max;

      private Builder() {
      }

      public Builder max(Integer max) {
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
