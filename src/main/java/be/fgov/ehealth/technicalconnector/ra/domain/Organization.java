package be.fgov.ehealth.technicalconnector.ra.domain;

import org.taktik.connector.technical.utils.IdentifierType;
import java.io.Serializable;
import org.apache.commons.lang.Validate;

public final class Organization implements Serializable {
   private static final long serialVersionUID = 1L;
   private String id;
   private IdentifierType type;
   private String name;
   ActorId identifier;
   private LocalizedText localizedName;

   public Organization() {
   }

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

   public void setId(String id) {
      this.id = id;
   }

   public IdentifierType getType() {
      return this.type;
   }

   public void setType(IdentifierType type) {
      this.type = type;
   }

   public void setName(String name) {
      this.name = name;
   }

   public LocalizedText getLocalizedName() {
      return this.localizedName;
   }

   public void setLocalizedName(LocalizedText localizedName) {
      this.localizedName = localizedName;
   }

   public ActorId getIdentifier() {
      return this.identifier;
   }

   public void setIdentifier(ActorId identifier) {
      this.identifier = identifier;
   }

   public String getName() {
      return this.name;
   }

   public String toString() {
      return "Organization [id=" + this.id + ", type=" + this.type.getType(48) + ", name=" + this.name + "]";
   }
}
