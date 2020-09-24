package be.business.connector.recipe.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum PrescriptionStatus {
   NotDelivered,
   InProcess,
   Delivered,
   Revoked,
   Expired,
   Archived;

   private static final Map<Integer, PrescriptionStatus> lookup = new HashMap();

   static {
      Iterator var1 = EnumSet.allOf(PrescriptionStatus.class).iterator();

      while(var1.hasNext()) {
         PrescriptionStatus s = (PrescriptionStatus)var1.next();
         lookup.put(s.ordinal(), s);
      }

   }

   public static PrescriptionStatus get(int code) {
      return (PrescriptionStatus)lookup.get(code);
   }
}
