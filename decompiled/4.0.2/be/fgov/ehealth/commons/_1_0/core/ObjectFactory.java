package be.fgov.ehealth.commons._1_0.core;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public Status createStatus() {
      return new Status();
   }

   public LocalisedString createLocalisedString() {
      return new LocalisedString();
   }

   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }
}
