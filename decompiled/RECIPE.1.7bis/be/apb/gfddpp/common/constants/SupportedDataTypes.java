package be.apb.gfddpp.common.constants;

public enum SupportedDataTypes {
   MEDICATION_HISTORY("medicationhistory"),
   MEDICATION_SCHEME_ENTRIES("medicationscheme_entries"),
   MEDICATION_SCHEME_TIMESTAMPS("medicationscheme_timestamps");

   private final String name;

   private SupportedDataTypes(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public static SupportedDataTypes lookupType(String name) {
      SupportedDataTypes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SupportedDataTypes supported = var1[var3];
         if (supported.getName().equals(name)) {
            return supported;
         }
      }

      throw new IllegalArgumentException();
   }
}
