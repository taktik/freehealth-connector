package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class CertificateIdentifier implements Serializable {
   private static final long serialVersionUID = 1L;
   private Actor actor;
   private String applicationId;

   private CertificateIdentifier(Builder builder) {
      this.actor = builder.actor;
      this.applicationId = builder.applicationId;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public Actor getActor() {
      return this.actor;
   }

   public String getApplicationId() {
      return this.applicationId;
   }

   public void setActor(Actor actor) {
      this.actor = actor;
   }

   public void setApplicationId(String applicationId) {
      this.applicationId = applicationId;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         CertificateIdentifier that = (CertificateIdentifier)o;
         return (new EqualsBuilder()).append(this.getActor(), that.getActor()).append(this.getApplicationId(), that.getApplicationId()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).append(this.getActor()).append(this.getApplicationId()).toHashCode();
   }

   // $FF: synthetic method
   CertificateIdentifier(Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      private Actor actor;
      private String applicationId;

      private Builder() {
      }

      public Builder actor(Actor actor) {
         this.actor = actor;
         return this;
      }

      public Builder applicationId(String applicationId) {
         this.applicationId = applicationId;
         return this;
      }

      public CertificateIdentifier build() {
         return new CertificateIdentifier(this);
      }

      // $FF: synthetic method
      Builder(Object x0) {
         this();
      }
   }
}
