package be.apb.gfddpp.common.constants;

public enum Services {
   AA("AA"),
   ASSURALIA("Assuralia"),
   ETK("ETK"),
   KGSS("KGSS"),
   PCDH("PCDH"),
   STS("SecureTokenService"),
   TIMESTAMP("TimeStamp"),
   TIP_SYSTEM("TipSystem");

   private final String name;

   private Services(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public static Services lookupType(String name) {
      Services[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Services services = arr$[i$];
         if (services.getName().equals(name)) {
            return services;
         }
      }

      throw new IllegalArgumentException();
   }
}
