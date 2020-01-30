package be.business.connector.gfddpp.domain.medicationscheme.protocol;

import be.business.connector.gfddpp.domain.medicationscheme.OrganizationInformation;
import be.business.connector.gfddpp.domain.medicationscheme.PersonInformation;

public abstract class BaseRequest extends Base {
   protected PersonInformation personInformation;
   protected OrganizationInformation organizationInformation;

   public PersonInformation getPersonInformation() {
      return this.personInformation;
   }

   public void setPersonInformation(PersonInformation personInformation) {
      this.personInformation = personInformation;
   }

   public OrganizationInformation getOrganizationInformation() {
      return this.organizationInformation;
   }

   public void setOrganizationInformation(OrganizationInformation organizationInformation) {
      this.organizationInformation = organizationInformation;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(super.toString());
      if (this.personInformation != null) {
         sb.append("\n\t personInformation: ");
         sb.append(this.personInformation);
      }

      if (this.organizationInformation != null) {
         sb.append("\n\t organizationInformation: ");
         sb.append(this.organizationInformation);
      }

      return sb.toString();
   }
}
