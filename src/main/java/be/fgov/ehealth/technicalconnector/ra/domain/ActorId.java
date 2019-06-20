package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ActorId implements Serializable {
   private static final long serialVersionUID = 1L;
   private String value;
   private String type;

   public ActorId() {
   }

   private ActorId(Builder builder) {
      this.value = builder.value;
      this.type = builder.type;
   }

   public String getValue() {
      return this.value;
   }

   public String getType() {
      return this.type;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public void setType(String type) {
      this.type = type;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ActorId actorId = (ActorId)o;
         return (new EqualsBuilder()).append(this.getValue(), actorId.getValue()).append(this.getType(), actorId.getType()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).append(this.getValue()).append(this.getType()).toHashCode();
   }

   // $FF: synthetic method
   ActorId(Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      private String value;
      private String type;

      private Builder() {
      }

      public Builder value(String value) {
         this.value = value;
         return this;
      }

      public Builder type(String type) {
         this.type = type;
         return this;
      }

      public ActorId build() {
         return new ActorId(this);
      }

      // $FF: synthetic method
      Builder(Object x0) {
         this();
      }
   }
}
