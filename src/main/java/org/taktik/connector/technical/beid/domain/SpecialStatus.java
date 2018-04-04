package org.taktik.connector.technical.beid.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum SpecialStatus implements Serializable {
   NO_STATUS("0", false, false, false),
   WHITE_CANE("1", true, false, false),
   EXTENDED_MINORITY("2", false, true, false),
   WHITE_CANE_EXTENDED_MINORITY("3", true, true, false),
   YELLOW_CANE("4", false, false, true),
   YELLOW_CANE_EXTENDED_MINORITY("5", false, true, true);

   private final String strValue;
   private final boolean whiteCane;
   private final boolean extendedMinority;
   private final boolean yellowCane;
   private static Map<String, SpecialStatus> map = new HashMap();

   private SpecialStatus(String strValue, boolean whiteCane, boolean extendedMinority, boolean yellowCane) {
      this.strValue = strValue;
      this.whiteCane = whiteCane;
      this.extendedMinority = extendedMinority;
      this.yellowCane = yellowCane;
   }

   public boolean hasWhiteCane() {
      return this.whiteCane;
   }

   public boolean hasExtendedMinority() {
      return this.extendedMinority;
   }

   public boolean hasYellowCane() {
      return this.yellowCane;
   }

   public boolean hasBadSight() {
      return this.whiteCane || this.yellowCane;
   }

   public static SpecialStatus toSpecialStatus(String value) {
      return (SpecialStatus)map.get(value);
   }

   static {
      SpecialStatus[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         SpecialStatus specialStatus = arr$[i$];
         String value = specialStatus.strValue;
         if (map.containsKey(value)) {
            throw new IllegalArgumentException("duplicate special status: " + value);
         }

         map.put(value, specialStatus);
      }

   }
}
