package be.fgov.ehealth.commons.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   public StreetType createStreetType() {
      return new StreetType();
   }

   public CountryType createCountryType() {
      return new CountryType();
   }

   public StatusType createStatusType() {
      return new StatusType();
   }

   public AddressType createAddressType() {
      return new AddressType();
   }

   public MunicipalityType createMunicipalityType() {
      return new MunicipalityType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public LocalisedString createLocalisedString() {
      return new LocalisedString();
   }
}
