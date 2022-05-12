package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class Actor implements Serializable {
   private static final long serialVersionUID = 1L;
   private List<ActorId> ids;
   private List<String> firstNames;
   private String name;

   public Actor() {
      this.ids = new ArrayList();
      this.firstNames = new ArrayList();
   }

   private Actor(Builder builder) {
      this.ids = new ArrayList();
      this.firstNames = new ArrayList();
      this.setIds(builder.ids);
      this.setFirstNames(builder.firstNames);
      this.setName(builder.name);
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public List<ActorId> getIds() {
      return this.ids;
   }

   public List<String> getFirstNames() {
      return this.firstNames;
   }

   public String getName() {
      return this.name;
   }

   public void setIds(List<ActorId> ids) {
      this.ids = ids;
   }

   public void setFirstNames(List<String> firstNames) {
      this.firstNames = firstNames;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Actor actor = (Actor)o;
         return (new EqualsBuilder()).append(this.getIds(), actor.getIds()).append(this.getFirstNames(), actor.getFirstNames()).append(this.getName(), actor.getName()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).append(this.getIds()).append(this.getFirstNames()).append(this.getName()).toHashCode();
   }

   public static final class Builder {
      private List<ActorId> ids;
      private List<String> firstNames;
      private String name;

      private Builder() {
      }

      public Builder ids(List<ActorId> ids) {
         this.ids = ids;
         return this;
      }

      public Builder firstNames(List<String> firstNames) {
         this.firstNames = firstNames;
         return this;
      }

      public Builder name(String name) {
         this.name = name;
         return this;
      }

      public Actor build() {
         return new Actor(this);
      }
   }
}
