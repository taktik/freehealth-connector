package be.business.connector.gfddpp.domain.medicationscheme;

public class Organization {
   private String actorIdValue;
   private Source actorIdSource;
   private String name;
   private String role;

   public String getActorIdValue() {
      return this.actorIdValue;
   }

   public void setActorIdValue(String actorIdValue) {
      this.actorIdValue = actorIdValue;
   }

   public Source getActorIdSource() {
      return this.actorIdSource;
   }

   public void setActorIdSource(Source actorIdSource) {
      this.actorIdSource = actorIdSource;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }
}
