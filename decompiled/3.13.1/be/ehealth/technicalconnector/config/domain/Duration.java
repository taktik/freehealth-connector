package be.ehealth.technicalconnector.config.domain;

import java.util.concurrent.TimeUnit;

public class Duration {
   private long duration;
   private TimeUnit sourceUnit;

   public Duration(long duration, TimeUnit sourceUnit) {
      this.duration = duration;
      this.sourceUnit = sourceUnit;
   }

   public long convert(TimeUnit targetUnit) {
      return targetUnit.convert(this.duration, this.sourceUnit);
   }
}
