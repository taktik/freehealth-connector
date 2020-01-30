package be.fgov.ehealth.commons.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public StatusType createStatusType() {
      return new StatusType();
   }

   public AddressType createAddressType() {
      return new AddressType();
   }

   public AddressType.Municipality createAddressTypeMunicipality() {
      return new AddressType.Municipality();
   }

   public LocalisedString createLocalisedString() {
      return new LocalisedString();
   }

   public AddressType.Country createAddressTypeCountry() {
      return new AddressType.Country();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   public AddressType.Street createAddressTypeStreet() {
      return new AddressType.Street();
   }
}
