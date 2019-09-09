package be.fgov.ehealth.commons.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public LocalisedString createLocalisedString() {
      return new LocalisedString();
   }

   public StatusType createStatusType() {
      return new StatusType();
   }

   public AddressType createAddressType() {
      return new AddressType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   public Street createStreet() {
      return new Street();
   }

   public Municipality createMunicipality() {
      return new Municipality();
   }

   public Country createCountry() {
      return new Country();
   }
}
