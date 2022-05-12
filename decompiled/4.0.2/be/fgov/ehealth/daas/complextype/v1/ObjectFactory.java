package be.fgov.ehealth.daas.complextype.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public Name createName() {
      return new Name();
   }

   public BaseNameType createBaseNameType() {
      return new BaseNameType();
   }

   public Actor createActor() {
      return new Actor();
   }

   public TypeCodeType createTypeCodeType() {
      return new TypeCodeType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }
}
