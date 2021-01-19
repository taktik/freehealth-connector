package be.ehealth.business.mycarenetdomaincommons.domain;

public class Nihii {
   private String quality;
   private String value;

   public Nihii(String quality, String value) {
      this.quality = quality;
      this.value = value;
   }

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String quality) {
      this.quality = quality;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
