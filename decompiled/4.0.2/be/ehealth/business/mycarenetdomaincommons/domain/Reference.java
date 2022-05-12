package be.ehealth.business.mycarenetdomaincommons.domain;

public class Reference {
   private String type;
   private String value;

   Reference(String type, String value) {
      this.type = type;
      this.value = value;
   }

   public static ReferenceBuilder builder() {
      return new ReferenceBuilder();
   }

   public String getType() {
      return this.type;
   }

   public String getValue() {
      return this.value;
   }

   public static class ReferenceBuilder {
      private String type;
      private String value;

      ReferenceBuilder() {
      }

      public ReferenceBuilder type(String type) {
         this.type = type;
         return this;
      }

      public ReferenceBuilder value(String value) {
         this.value = value;
         return this;
      }

      public Reference build() {
         return new Reference(this.type, this.value);
      }

      public String toString() {
         return "Reference.ReferenceBuilder(type=" + this.type + ", value=" + this.value + ")";
      }
   }
}
