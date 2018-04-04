package org.taktik.connector.business.recipeprojects.core.utils;

public enum Qualities {
   DOCTOR("DOCTOR"),
   NURSE("NURSE"),
   MIDWIFE("MIDWIFE"),
   CITIZEN("CITIZEN"),
   DENTIST("DENTIST"),
   PHARMACY("PHARMACY"),
   HOSPITAL("HOSPITAL");

   private final String value;

   private Qualities(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static Qualities fromValue(String v) {
      Qualities[] var4;
      int var3 = (var4 = values()).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Qualities c = var4[var2];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
