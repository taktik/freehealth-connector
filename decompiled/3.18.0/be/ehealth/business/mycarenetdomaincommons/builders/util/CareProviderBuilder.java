package be.ehealth.business.mycarenetdomaincommons.builders.util;

import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;

public class CareProviderBuilder {
   private CareProvider careProvider;

   public CareProviderBuilder(String quality, String id) {
      this.careProvider = new CareProvider(new Nihii(quality, id));
   }

   public final CareProviderBuilder addPhysicalPersonIdentification(Identification identification) {
      this.checkPhysicalPersonNotFilledOutYet();
      this.careProvider.setPhysicalPerson(identification);
      return this;
   }

   public final CareProviderBuilder addOrganisationIdentification(Identification identification) {
      this.checkOrganisationNotFilledOutYet();
      this.careProvider.setOrganization(identification);
      return this;
   }

   public final CareProviderBuilder addPhysicalPersonWithSsin(String name, String ssin) {
      this.checkPhysicalPersonNotFilledOutYet();
      this.careProvider.setPhysicalPerson(new Identification(name, (Nihii)null, ssin, (String)null));
      return this;
   }

   private void checkPhysicalPersonNotFilledOutYet() {
      if (this.careProvider.getPhysicalPerson() != null) {
         throw new IllegalStateException("error while building careprovider : addPhysicalPerson called while physical person already filled out");
      }
   }

   public final CareProviderBuilder addPhysicalPersonWithNihii(String name, String quality, String value) {
      this.checkPhysicalPersonNotFilledOutYet();
      this.careProvider.setPhysicalPerson(new Identification(name, new Nihii(quality, value), (String)null, (String)null));
      return this;
   }

   public final CareProviderBuilder addOrganisationWithNihii(String name, String quality, String value) {
      this.checkOrganisationNotFilledOutYet();
      this.careProvider.setOrganization(new Identification(name, new Nihii(quality, value), (String)null, (String)null));
      return this;
   }

   private void checkOrganisationNotFilledOutYet() {
      if (this.careProvider.getOrganization() != null) {
         throw new IllegalStateException("error while building careprovider : addOrganisation called while organisation already filled out");
      }
   }

   public final CareProviderBuilder addOrganisationWithCbe(String name, String cbeNumber) {
      this.checkOrganisationNotFilledOutYet();
      this.careProvider.setOrganization(new Identification(name, (Nihii)null, (String)null, cbeNumber));
      return this;
   }

   public final CareProvider build() {
      return this.careProvider;
   }
}
