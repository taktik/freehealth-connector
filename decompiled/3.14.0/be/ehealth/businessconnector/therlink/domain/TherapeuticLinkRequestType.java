package be.ehealth.businessconnector.therlink.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

public abstract class TherapeuticLinkRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   private TherapeuticLink link;
   private List<Proof> proofs;
   private String externalId;
   private DateTime requestDate;
   private Author author;

   public TherapeuticLinkRequestType() {
   }

   /** @deprecated */
   @Deprecated
   public TherapeuticLinkRequestType(String id, Date date, Author author, TherapeuticLink link, Proof... proofs) {
      this(id, new DateTime(date), author, link, proofs);
   }

   public TherapeuticLinkRequestType(String id, DateTime date, Author author, TherapeuticLink link, Proof... proofs) {
      this.externalId = id;
      this.requestDate = date;
      this.author = author;
      this.link = link;
      if (proofs != null) {
         this.proofs = Arrays.asList(proofs);
      }

   }

   public TherapeuticLink getLink() {
      return this.link;
   }

   public void setLink(TherapeuticLink link) {
      this.link = link;
   }

   public List<Proof> getProofs() {
      if (this.proofs == null) {
         this.proofs = new ArrayList();
      }

      return this.proofs;
   }

   public void setProofs(List<Proof> proofs) {
      this.proofs = proofs;
   }

   public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();
      builder.append(this.link);
      builder.append(this.proofs);
      builder.append(this.author);
      builder.append(this.requestDate);
      builder.append(this.externalId);
      return builder.toHashCode();
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof TherapeuticLinkRequestType)) {
         return false;
      } else if (obj == this) {
         return true;
      } else {
         TherapeuticLinkRequestType other = (TherapeuticLinkRequestType)obj;
         EqualsBuilder builder = new EqualsBuilder();
         builder.append(this.link, other.link);
         builder.append(this.proofs, other.proofs);
         builder.append(this.author, other.author);
         builder.append(this.requestDate, other.requestDate);
         builder.append(this.externalId, other.externalId);
         return builder.isEquals();
      }
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append(this.link);
      builder.append(this.proofs);
      builder.append(this.author);
      builder.append(this.requestDate);
      builder.append(this.externalId);
      return builder.toString();
   }

   public String getID() {
      return this.externalId;
   }

   public Author getAuthor() {
      return this.author;
   }

   /** @deprecated */
   @Deprecated
   public Date getDate() {
      return this.requestDate == null ? null : this.requestDate.toDate();
   }

   public DateTime getDateTime() {
      return this.requestDate;
   }
}
