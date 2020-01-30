package be.business.connector.gfddpp.domain.medicationscheme;

public class PersonInformation {
   private String firstName;
   private String lastName;
   private String ssin;
   private String nihii;
   private String role;

   public PersonInformation() {
   }

   public PersonInformation(String firstName, String lastName, String ssin) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.ssin = ssin;
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

   public PersonInformation withNihii(String nihii) {
      this.setNihii(nihii);
      return this;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Person Information");
      sb.append("\n\t firstname: ");
      sb.append(this.firstName);
      sb.append("\n\t lastname: ");
      sb.append(this.lastName);
      sb.append("\n\t ssin: ");
      sb.append(this.ssin);
      sb.append("\n\t nihii: ");
      sb.append(this.nihii);
      sb.append("\n\t role: ");
      sb.append(this.role);
      return sb.toString();
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }
}
