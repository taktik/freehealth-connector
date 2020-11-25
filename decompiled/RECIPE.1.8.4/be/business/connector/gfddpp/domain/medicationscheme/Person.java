package be.business.connector.gfddpp.domain.medicationscheme;

public class Person {
   private String ssin;
   private String nihii;
   private String firstName;
   private String lastName;
   private String role;

   public String getSsin() {
      return this.ssin;
   }

   public void setSsin(String ssin) {
      this.ssin = ssin;
   }

   public String getNihii() {
      return this.nihii;
   }

   public void setNihii(String nihii) {
      this.nihii = nihii;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }
}
