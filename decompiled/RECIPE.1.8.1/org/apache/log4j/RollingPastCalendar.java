package org.apache.log4j;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RollingPastCalendar extends CustomRollingCallendar {
   private static final long serialVersionUID = -3560331770601814179L;

   RollingPastCalendar() {
   }

   RollingPastCalendar(TimeZone tz, Locale locale) {
      super(tz, locale);
   }

   public long getPastCheckMillis(Date now, int maxBackupIndex) {
      return this.getPastDate(now, maxBackupIndex).getTime();
   }

   public Date getPastDate(Date now, int maxBackupIndex) {
      this.setTime(now);
      switch(this.type) {
      case 0:
         this.set(13, this.get(13));
         this.set(14, this.get(14));
         this.set(12, this.get(12) - maxBackupIndex);
         break;
      case 1:
         this.set(12, this.get(12));
         this.set(13, this.get(13));
         this.set(14, this.get(14));
         this.set(11, this.get(11) - maxBackupIndex);
         break;
      case 2:
         this.set(12, this.get(12));
         this.set(13, this.get(13));
         this.set(14, this.get(14));
         int hour = this.get(11);
         if (hour < 12) {
            this.set(11, 12);
         } else {
            this.set(11, 0);
         }

         this.set(5, this.get(5) - maxBackupIndex);
         break;
      case 3:
         this.set(11, this.get(11));
         this.set(12, this.get(12));
         this.set(13, this.get(13));
         this.set(14, this.get(14));
         this.set(5, this.get(5) - maxBackupIndex);
         break;
      case 4:
         this.set(7, this.getFirstDayOfWeek());
         this.set(11, this.get(11));
         this.set(12, this.get(12));
         this.set(13, this.get(13));
         this.set(14, this.get(14));
         this.set(3, this.get(3) - maxBackupIndex);
         break;
      case 5:
         this.set(5, this.get(5));
         this.set(11, this.get(11));
         this.set(12, this.get(12));
         this.set(13, this.get(13));
         this.set(14, this.get(14));
         this.set(2, this.get(2) - maxBackupIndex);
         break;
      default:
         throw new IllegalStateException("Unknown periodicity type.");
      }

      return this.getTime();
   }
}
