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
      SupportedDataTypes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         SupportedDataTypes supported = arr$[i$];
         if (supported.getName().equals(name)) {
            return supported;
         }
      }

      throw new IllegalArgumentException();
   }
}
