package be.ehealth.business.mycarenetdomaincommons.domain;

public class Attribute {
   private String key;
   private Object value;

   Attribute(String key, Object value) {
      this.key = key;
      this.value = value;
   }

   public static AttributeBuilder builder() {
      return new AttributeBuilder();
   }

   public String getKey() {
      return this.key;
   }

   public Object getValue() {
      return this.value;
   }

   public static class AttributeBuilder {
      private String key;
      private Object value;

      AttributeBuilder() {
      }

      public AttributeBuilder key(String key) {
         this.key = key;
         return this;
      }

      public AttributeBuilder value(Object value) {
         this.value = value;
         return this;
      }

      public Attribute build() {
         return new Attribute(this.key, this.value);
      }

      public String toString() {
         return "Attribute.AttributeBuilder(key=" + this.key + ", value=" + this.value + ")";
      }
   }
}
