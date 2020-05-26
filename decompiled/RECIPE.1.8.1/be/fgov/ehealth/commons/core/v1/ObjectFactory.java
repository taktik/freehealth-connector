package be.fgov.ehealth.commons.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public StatusType createStatusType() {
      return new StatusType();
   }

   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   public LocalisedString createLocalisedString() {
      return new LocalisedString();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public AddressType createAddressType() {
      return new AddressType();
   }

   public StreetType createStreetType() {
      return new StreetType();
   }

   public MunicipalityType createMunicipalityType() {
      return new MunicipalityType();
   }

   public CountryType createCountryType() {
      return new CountryType();
   }
}
