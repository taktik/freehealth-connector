package be.fgov.ehealth.dics.core.v4.compounding.common;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public CompoundingIngredientKeyType createCompoundingIngredientKeyType() {
      return new CompoundingIngredientKeyType();
   }

   public CompoundingFormulaKeyType createCompoundingFormulaKeyType() {
      return new CompoundingFormulaKeyType();
   }

   public SynonymType createSynonymType() {
      return new SynonymType();
   }
}
