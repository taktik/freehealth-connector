package org.apache.log4j;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class CustomRollingCallendar extends GregorianCalendar {
   private static final long serialVersionUID = -3560331770601814177L;
   int type = -1;

   CustomRollingCallendar() {
   }

   CustomRollingCallendar(TimeZone tz, Locale locale) {
      super(tz, locale);
   }

   void setType(int type) {
      this.type = type;
   }

   public long getNextCheckMillis(Date now) {
      return this.getNextCheckDate(now).getTime();
   }

   public Date getNextCheckDate(Date now) {
      this.setTime(now);
      switch(this.type) {
      case 0:
         this.set(13, 0);
         this.set(14, 0);
         this.add(12, 1);
         break;
      case 1:
         this.set(12, 0);
         this.set(13, 0);
         this.set(14, 0);
         this.add(11, 1);
         break;
      case 2:
         this.set(12, 0);
         this.set(13, 0);
         this.set(14, 0);
         int hour = this.get(11);
         if (hour < 12) {
            this.set(11, 12);
         } else {
            this.set(11, 0);
            this.add(5, 1);
         }
         break;
      case 3:
         this.set(11, 0);
         this.set(12, 0);
         this.set(13, 0);
         this.set(14, 0);
         this.add(5, 1);
         break;
      case 4:
         this.set(7, this.getFirstDayOfWeek());
         this.set(11, 0);
         this.set(12, 0);
         this.set(13, 0);
         this.set(14, 0);
         this.add(3, 1);
         break;
      case 5:
         this.set(5, 1);
         this.set(11, 0);
         this.set(12, 0);
         this.set(13, 0);
         this.set(14, 0);
         this.add(2, 1);
         break;
      default:
         throw new IllegalStateException("Unknown periodicity type.");
      }

      return this.getTime();
   }
}
