package be.fgov.ehealth.dics.core.v3.actual.common;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public DmppKeyType createDmppKeyType() {
      return new DmppKeyType();
   }

   public RealActualIngredientKeyType createRealActualIngredientKeyType() {
      return new RealActualIngredientKeyType();
   }

   public PackAmountType createPackAmountType() {
      return new PackAmountType();
   }

   public RealActualIngredientEquivalentKeyType createRealActualIngredientEquivalentKeyType() {
      return new RealActualIngredientEquivalentKeyType();
   }

   public AmppComponentKeyType createAmppComponentKeyType() {
      return new AmppComponentKeyType();
   }

   public DerogationImportKeyType createDerogationImportKeyType() {
      return new DerogationImportKeyType();
   }

   public AmppComponentEquivalentKeyType createAmppComponentEquivalentKeyType() {
      return new AmppComponentEquivalentKeyType();
   }
}
