package be.fgov.ehealth.technicalconnector.ra.domain;

import org.taktik.connector.technical.utils.IdentifierType;
import org.apache.commons.lang.Validate;

public final class Organization {
   private String id;
   private IdentifierType type;
   private String name;

   public Organization(String id, IdentifierType type, String name) {
      Validate.notEmpty(id);
      Validate.notNull(type);
      Validate.notEmpty(name);
      this.id = id;
      this.name = name;
      this.type = type;
   }

   public String getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public IdentifierType getType() {
      return this.type;
   }

   public String toString() {
      return "Organization [id=" + this.id + ", type=" + this.type.getType(48) + ", name=" + this.name + "]";
   }
}
