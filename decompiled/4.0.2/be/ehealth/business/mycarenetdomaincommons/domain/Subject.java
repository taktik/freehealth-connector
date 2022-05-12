package be.ehealth.business.mycarenetdomaincommons.domain;

import java.util.List;

public class Subject {
   private String type;
   private String subType;
   private List<Identifier> identifiers;
   private List<Attribute> attributes;

   Subject(String type, String subType, List<Identifier> identifiers, List<Attribute> attributes) {
      this.type = type;
      this.subType = subType;
      this.identifiers = identifiers;
      this.attributes = attributes;
   }

   public static SubjectBuilder builder() {
      return new SubjectBuilder();
   }

   public String getType() {
      return this.type;
   }

   public String getSubType() {
      return this.subType;
   }

   public List<Identifier> getIdentifiers() {
      return this.identifiers;
   }

   public List<Attribute> getAttributes() {
      return this.attributes;
   }

   public static class SubjectBuilder {
      private String type;
      private String subType;
      private List<Identifier> identifiers;
      private List<Attribute> attributes;

      SubjectBuilder() {
      }

      public SubjectBuilder type(String type) {
         this.type = type;
         return this;
      }

      public SubjectBuilder subType(String subType) {
         this.subType = subType;
         return this;
      }

      public SubjectBuilder identifiers(List<Identifier> identifiers) {
         this.identifiers = identifiers;
         return this;
      }

      public SubjectBuilder attributes(List<Attribute> attributes) {
         this.attributes = attributes;
         return this;
      }

      public Subject build() {
         return new Subject(this.type, this.subType, this.identifiers, this.attributes);
      }

      public String toString() {
         return "Subject.SubjectBuilder(type=" + this.type + ", subType=" + this.subType + ", identifiers=" + this.identifiers + ", attributes=" + this.attributes + ")";
      }
   }
}
