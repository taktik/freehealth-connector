package be.business.connector.gfddpp.domain;

public enum ThreeStateBoolean {
   TRUE(Boolean.TRUE),
   FALSE(Boolean.FALSE),
   NULL((Boolean)null);

   private Boolean value;

   private ThreeStateBoolean(Boolean value) {
      this.value = value;
   }

   public Boolean getBooleanValue() {
      return this.value;
   }
}
