package be.ehealth.businessconnector.therlink.util;

public enum TherlinkConfigKeys {
   THERLINK_CAREPROVIDER_HCPARTY("careprovider.in.therapeuticlink");

   private String key;

   private TherlinkConfigKeys(String key) {
      this.key = key;
   }

   public String getKey() {
      return this.key;
   }

   public String toString() {
      return this.key;
   }
}
