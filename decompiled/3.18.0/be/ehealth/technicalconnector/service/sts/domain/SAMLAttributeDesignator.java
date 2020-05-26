package be.ehealth.technicalconnector.service.sts.domain;

public class SAMLAttributeDesignator {
   private String name;
   private String namespace;

   public SAMLAttributeDesignator(String name, String namespace) {
      this.name = name;
      this.namespace = namespace;
   }

   public String getName() {
      return this.name;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public String toString() {
      return "SAMLAttributeDesignator [name=" + this.name + ", namespace=" + this.namespace + "]";
   }
}
