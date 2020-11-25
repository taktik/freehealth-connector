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
      Services[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Services services = var1[var3];
         if (services.getName().equals(name)) {
            return services;
         }
      }

      throw new IllegalArgumentException();
   }
}
