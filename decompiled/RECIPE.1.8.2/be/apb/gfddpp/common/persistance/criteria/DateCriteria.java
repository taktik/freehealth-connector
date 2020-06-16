package be.apb.gfddpp.common.persistance.criteria;

import java.lang.reflect.Field;
import java.util.Date;

public class DateCriteria extends Criteria<DateRange> {
   public DateCriteria(Field field, DateRange value) {
      super(field, value);
   }

   public boolean isValid() {
      return !this.isEmpty() && ((DateRange)super.getValue()).isValidRange();
   }

   public boolean isRange() {
      return ((DateRange)super.getValue()).isRange();
   }

   public Date getStart() {
      return ((DateRange)super.getValue()).getStart();
   }

   public Date getEnd() {
      return ((DateRange)super.getValue()).getEnd();
   }
}
