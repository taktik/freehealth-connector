package be.business.connector.gfddpp.domain.medicationscheme;

public class OrganizationInformation {
   private String role;
   private String name;
   private String idType;
   private String idValue;

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(super.toString());
      sb.append("\n\t role: ");
      sb.append(this.role);
      sb.append("\n\t name: ");
      sb.append(this.name);
      sb.append("\n\t idType: ");
      sb.append(this.idType);
      sb.append("\n\t idValue: ");
      sb.append(this.idValue);
      return sb.toString();
   }

   public String getIdType() {
      return this.idType;
   }

   public void setIdType(String idType) {
      this.idType = idType;
   }

   public String getIdValue() {
      return this.idValue;
   }

   public void setIdValue(String idValue) {
      this.idValue = idValue;
   }
}
