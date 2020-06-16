package be.apb.gfddpp.common.persistance.criteria;

import java.util.Date;

public class DateRange {
   private Date start;
   private Date end;

   public DateRange(Date d1, Date d2) {
      if (d1 != null || d2 != null) {
         if (d1 == null && d2 != null) {
            this.start = d2;
         } else if (d1 != null && d2 == null) {
            this.start = d1;
         } else if (d1.after(d2)) {
            this.start = d2;
            this.end = d1;
         } else {
            this.start = d1;
            this.end = d2;
         }
      }

   }

   public boolean isRange() {
      return this.start != null && this.end != null;
   }

   public Date getStart() {
      return this.start;
   }

   public Date getEnd() {
      return this.end;
   }

   public boolean isValidRange() {
      if (!this.isRange()) {
         return this.getStart() != null;
      } else {
         return this.getStart() != null && this.getEnd() != null;
      }
   }
}
