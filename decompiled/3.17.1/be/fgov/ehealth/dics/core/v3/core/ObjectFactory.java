package be.fgov.ehealth.dics.core.v3.core;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public VmpKeyType createVmpKeyType() {
      return new VmpKeyType();
   }

   public Text255Type createText255Type() {
      return new Text255Type();
   }

   public RangeType createRangeType() {
      return new RangeType();
   }

   public QuantityType createQuantityType() {
      return new QuantityType();
   }

   public VmpComponentKeyType createVmpComponentKeyType() {
      return new VmpComponentKeyType();
   }

   public StrengthRangeType createStrengthRangeType() {
      return new StrengthRangeType();
   }

   public StrengthType createStrengthType() {
      return new StrengthType();
   }
}
