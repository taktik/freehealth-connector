package be.ehealth.technicalconnector.config.domain;

import java.util.concurrent.TimeUnit;

public class Duration {
   private long value;
   private TimeUnit timeUnit;

   public Duration(long value, TimeUnit timeUnit) {
      this.value = value;
      this.timeUnit = timeUnit;
   }

   public long convert(TimeUnit targetUnit) {
      return targetUnit.convert(this.value, this.timeUnit);
   }
}
