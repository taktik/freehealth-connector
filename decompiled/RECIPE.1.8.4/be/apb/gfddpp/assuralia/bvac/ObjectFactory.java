package be.apb.gfddpp.assuralia.bvac;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public TipInsurerCfg createTipInsurerCfg() {
      return new TipInsurerCfg();
   }

   public Parties createParties() {
      return new Parties();
   }

   public TipInsurerCfg.Insurer createTipInsurerCfgInsurer() {
      return new TipInsurerCfg.Insurer();
   }

   public Parties.Party createPartiesParty() {
      return new Parties.Party();
   }
}
