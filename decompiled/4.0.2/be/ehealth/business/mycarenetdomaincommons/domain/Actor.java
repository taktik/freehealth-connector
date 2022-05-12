package be.ehealth.business.mycarenetdomaincommons.domain;

import java.util.List;

public class Actor extends Subject {
   private String role;

   public Actor(String type, String subType, List<Identifier> identifiers, List<Attribute> attributes, String role) {
      super(type, subType, identifiers, attributes);
      this.role = role;
   }

   public static ActorBuilder actorBuilder() {
      return new ActorBuilder();
   }

   public String getRole() {
      return this.role;
   }

   public static class ActorBuilder {
      private String type;
      private String subType;
      private List<Identifier> identifiers;
      private List<Attribute> attributes;
      private String role;

      ActorBuilder() {
      }

      public ActorBuilder type(String type) {
         this.type = type;
         return this;
      }

      public ActorBuilder subType(String subType) {
         this.subType = subType;
         return this;
      }

      public ActorBuilder identifiers(List<Identifier> identifiers) {
         this.identifiers = identifiers;
         return this;
      }

      public ActorBuilder attributes(List<Attribute> attributes) {
         this.attributes = attributes;
         return this;
      }

      public ActorBuilder role(String role) {
         this.role = role;
         return this;
      }

      public Actor build() {
         return new Actor(this.type, this.subType, this.identifiers, this.attributes, this.role);
      }

      public String toString() {
         return "Actor.ActorBuilder(type=" + this.type + ", subType=" + this.subType + ", identifiers=" + this.identifiers + ", attributes=" + this.attributes + ", role=" + this.role + ")";
      }
   }
}
