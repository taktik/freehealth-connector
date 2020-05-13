package be.ehealth.technicalconnector.beid.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum SpecialOrganisation implements Serializable {
   UNSPECIFIED((String)null),
   SHAPE("1"),
   NATO("2"),
   FORMER_BLUE_CARD_HOLDER("4"),
   RESEARCHER("5"),
   UNKNOWN((String)null);

   private final String key;
   private static Map<String, SpecialOrganisation> specialOrganisations = new HashMap();

   private SpecialOrganisation(String key) {
      this.key = key;
   }

   public String getKey() {
      return this.key;
   }

   public static SpecialOrganisation toSpecialOrganisation(String key) {
      if (null == key) {
         return UNSPECIFIED;
      } else if (key.isEmpty()) {
         return UNSPECIFIED;
      } else {
         SpecialOrganisation specialOrganisation = (SpecialOrganisation)specialOrganisations.get(key);
         return null == specialOrganisation ? UNKNOWN : specialOrganisation;
      }
   }

   static {
      SpecialOrganisation[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         SpecialOrganisation specialOrganisation = arr$[i$];
         String key = specialOrganisation.getKey();
         if (key != null) {
            if (specialOrganisations.containsKey(key)) {
               throw new IllegalArgumentException("duplicate key for special organisation type: " + key);
            }

            specialOrganisations.put(key, specialOrganisation);
         }
      }

   }
}
