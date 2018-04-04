package be.ehealth.business.mycarenetdomaincommons.domain;

public class Party {
   private Identification physicalPerson;
   private Identification organization;

   public Identification getPhysicalPerson() {
      return this.physicalPerson;
   }

   public void setPhysicalPerson(Identification physicalPerson) {
      this.physicalPerson = physicalPerson;
   }

   public Identification getOrganization() {
      return this.organization;
   }

   public void setOrganization(Identification organization) {
      this.organization = organization;
   }
}
