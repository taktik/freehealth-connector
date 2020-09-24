package be.fgov.ehealth.commons.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public MunicipalityType createMunicipalityType() {
      return new MunicipalityType();
   }

   public StreetType createStreetType() {
      return new StreetType();
   }

   public LocalisedString createLocalisedString() {
      return new LocalisedString();
   }

   public CountryType createCountryType() {
      return new CountryType();
   }

   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public StatusType createStatusType() {
      return new StatusType();
   }

   public AddressType createAddressType() {
      return new AddressType();
   }
}
