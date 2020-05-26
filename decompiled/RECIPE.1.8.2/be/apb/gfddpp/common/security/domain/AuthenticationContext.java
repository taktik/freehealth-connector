package be.apb.gfddpp.common.security.domain;

public class AuthenticationContext {
   private Boolean pharmacist;
   private Boolean strongPatientAuthentication;
   private Boolean patientConsent;
   private Boolean therapeuticalRelationShip;

   public Boolean isPatientConsent() {
      return this.patientConsent;
   }

   public void setPatientConsent(Boolean patientConsent) {
      this.patientConsent = patientConsent;
   }

   public Boolean isTherapeuticalRelationShip() {
      return this.therapeuticalRelationShip;
   }

   public void setTherapeuticalRelationShip(Boolean therapeuticalRelationShip) {
      this.therapeuticalRelationShip = therapeuticalRelationShip;
   }

   public Boolean isPharmacist() {
      return this.pharmacist;
   }

   public void setPharmacist(Boolean pharmacist) {
      this.pharmacist = pharmacist;
   }

   public Boolean isStrongPatientAuthentication() {
      return this.strongPatientAuthentication;
   }

   public void setStrongPatientAuthentication(Boolean strongPatientAuthentication) {
      this.strongPatientAuthentication = strongPatientAuthentication;
   }
}
