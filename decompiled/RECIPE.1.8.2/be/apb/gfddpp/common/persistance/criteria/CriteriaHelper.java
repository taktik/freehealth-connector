package be.apb.gfddpp.common.persistance.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CriteriaHelper {
   public static List<DateCriteria> getDateCriterias(List<Criteria> criteria) {
      if (!criteria.isEmpty()) {
         List<DateCriteria> dates = new ArrayList();
         Iterator var2 = criteria.iterator();

         while(var2.hasNext()) {
            Criteria crit = (Criteria)var2.next();
            if (crit instanceof DateCriteria && ((DateCriteria)crit).isValid()) {
               dates.add((DateCriteria)crit);
            }
         }

         return dates;
      } else {
         return null;
      }
   }

   public static List<Criteria> getEnumCriterias(List<Criteria> criteria) {
      if (!criteria.isEmpty()) {
         List<Criteria> enums = new ArrayList();
         Iterator var2 = criteria.iterator();

         while(var2.hasNext()) {
            Criteria crit = (Criteria)var2.next();
            if (crit.isEnum()) {
               enums.add(crit);
            }
         }

         return enums;
      } else {
         return null;
      }
   }
}
