package be.ehealth.technicalconnector.service.sts.domain;

import org.apache.commons.lang3.ArrayUtils;

public class SAMLAttribute {
   private String name;
   private String namespace;
   private String[] value;

   public SAMLAttribute(String name, String namespace, String... value) {
      this.name = name;
      this.namespace = namespace;
      this.value = value;
   }

   public String getName() {
      return this.name;
   }

   public String getNamespace() {
      return this.namespace;
   }

   /** @deprecated */
   @Deprecated
   public String getValue() {
      return ArrayUtils.isNotEmpty(this.value) ? this.value[0] : null;
   }

   public String[] getValues() {
      return (String[])((String[])ArrayUtils.clone(this.value));
   }

   public String toString() {
      return "SAMLAttribute [name=" + this.name + ", namespace=" + this.namespace + ", value=" + ArrayUtils.toString(this.value) + "]";
   }
}
