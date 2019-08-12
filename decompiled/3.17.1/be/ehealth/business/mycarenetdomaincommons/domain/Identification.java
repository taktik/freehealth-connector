package be.ehealth.business.mycarenetdomaincommons.domain;

public class Identification {
   private String name;
   private Nihii nihii;
   private String ssin;
   private String cbe;

   public Identification() {
   }

   public Identification(String name) {
      this.name = name;
   }

   public Identification(String name, Nihii nihii, String ssin, String cbe) {
      this.name = name;
      this.nihii = nihii;
      this.ssin = ssin;
      this.cbe = cbe;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public Nihii getNihii() {
      return this.nihii;
   }

   public void setNihii(Nihii nihii) {
      this.nihii = nihii;
   }

   public String getSsin() {
      return this.ssin;
   }

   public void setSsin(String ssin) {
      this.ssin = ssin;
   }

   public String getCbe() {
      return this.cbe;
   }

   public void setCbe(String cbe) {
      this.cbe = cbe;
   }
}
