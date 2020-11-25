package be.ehealth.business.mycarenetdomaincommons.domain;

public class CareProvider extends Party {
   private Nihii nihii;

   public CareProvider(Nihii nihii) {
      this.nihii = nihii;
   }

   public Nihii getNihii() {
      return this.nihii;
   }

   public void setNihii(Nihii nihii) {
      this.nihii = nihii;
   }
}
