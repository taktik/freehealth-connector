package be.ehealth.businessconnector.genericasync.domain;

public class TackQuery {
   private Integer max;

   private TackQuery(Builder builder) {
      this.max = builder.max;
   }

   public static Builder newBuilder() {
      return new Builder();
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
   }
}
