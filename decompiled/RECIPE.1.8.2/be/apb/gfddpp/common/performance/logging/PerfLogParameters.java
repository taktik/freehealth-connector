package be.apb.gfddpp.common.performance.logging;

public enum PerfLogParameters {
   CLASS("class"),
   METHOD("method"),
   START_TIMESTAMP("starttimestamp"),
   END_TIMESTAMP("endtimestamp"),
   DURATION("duration"),
   SGUID("sguid"),
   UNIQUE_ID("uniqueId");

   private String value;

   private PerfLogParameters(String value) {
      this.value = value;
   }

   public String getValue() {
      return this.value;
   }
}
