package be.ehealth.businessconnector.therlink.domain;

import be.ehealth.businessconnector.therlink.domain.requests.TherapeuticLinkStatus;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class TherapeuticLink implements Serializable {
   private static final long serialVersionUID = 1L;
   protected be.ehealth.business.common.domain.Patient patient;
   protected HcParty hcParty;
   protected String type;
   protected LocalDate startDate;
   protected LocalDate endDate;
   protected String comment;
   protected TherapeuticLinkStatus status;

   public TherapeuticLink(be.ehealth.business.common.domain.Patient patient, HcParty hcParty, String type) {
      this.patient = patient;
      this.hcParty = hcParty;
      this.type = type;
   }

   public TherapeuticLink() {
   }

   public be.ehealth.business.common.domain.Patient getPatient() {
      return this.patient;
   }

   public void setPatient(be.ehealth.business.common.domain.Patient patient) {
      this.patient = patient;
   }

   public HcParty getHcParty() {
      return this.hcParty;
   }

   public void setHcParty(HcParty hcParty) {
      this.hcParty = hcParty;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public LocalDate getStartDate() {
      return this.startDate;
   }

   public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
   }

   public LocalDate getEndDate() {
      return this.endDate;
   }

   public void setEndDate(LocalDate endDate) {
      this.endDate = endDate;
   }

   public String getComment() {
      return this.comment;
   }

   public void setComment(String comment) {
      this.comment = comment;
   }

   public TherapeuticLinkStatus getStatus() {
      return this.status;
   }

   public void setStatus(TherapeuticLinkStatus status) {
      this.status = status;
   }

   public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();
      builder.append(this.comment);
      builder.append(this.endDate);
      builder.append(this.hcParty);
      builder.append(this.patient);
      builder.append(this.startDate);
      builder.append(this.status);
      return builder.toHashCode();
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof TherapeuticLink)) {
         return false;
      } else if (obj == this) {
         return true;
      } else {
         TherapeuticLink other = (TherapeuticLink)obj;
         EqualsBuilder builder = new EqualsBuilder();
         builder.append(this.comment, other.comment);
         builder.append(this.endDate, other.endDate);
         builder.append(this.hcParty, other.hcParty);
         builder.append(this.patient, other.patient);
         builder.append(this.startDate, other.startDate);
         builder.append(this.status, other.status);
         return builder.isEquals();
      }
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append(this.comment);
      builder.append(this.endDate);
      builder.append(this.hcParty);
      builder.append(this.patient);
      builder.append(this.startDate);
      builder.append(this.status);
      return builder.toString();
   }

   public static class Builder {
      private TherapeuticLink link = new TherapeuticLink();

      public Builder() {
      }

      public Builder withPatient(be.ehealth.business.common.domain.Patient patient) {
         this.link.setPatient(patient);
         return this;
      }

      public Builder withHcParty(HcParty hcp) {
         this.link.setHcParty(hcp);
         return this;
      }

      public Builder withStartDate(LocalDate date) {
         if (date != null) {
            this.link.setStartDate(date);
         }

         return this;
      }

      public Builder withEndDate(LocalDate date) {
         if (date != null) {
            this.link.setEndDate(date);
         }

         return this;
      }

      public Builder withStartDateTime(DateTime date) {
         if (date != null) {
            this.link.setStartDate(new LocalDate(date.getMillis()));
         }

         return this;
      }

      public Builder withEndDateTime(DateTime date) {
         if (date != null) {
            this.link.setEndDate(new LocalDate(date.getMillis()));
         }

         return this;
      }

      public Builder withType(String type) {
         this.link.setType(type);
         return this;
      }

      public Builder withComment(String comment) {
         this.link.setComment(comment);
         return this;
      }

      public TherapeuticLink build() {
         return this.link;
      }

      public Builder withStatus(TherapeuticLinkStatus status) {
         this.link.setStatus(status);
         return this;
      }
   }
}
