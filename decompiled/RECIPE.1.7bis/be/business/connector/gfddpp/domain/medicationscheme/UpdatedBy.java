package be.business.connector.gfddpp.domain.medicationscheme;

public class UpdatedBy {
   private Organization organization;
   private Person person;

   public Organization getOrganization() {
      return this.organization;
   }

   public void setOrganization(Organization organization) {
      this.organization = organization;
   }

   public Person getPerson() {
      return this.person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }
}
