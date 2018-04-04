package be.ehealth.businessconnector.therlink.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

public class OperationContext implements Serializable {
   private static final long serialVersionUID = 1L;
   private String operation;
   private DateTime recordDate;
   private Author author;
   private List<Proof> proofs;

   /** @deprecated */
   @Deprecated
   public OperationContext(String operation, Calendar recordDate, Author author, List<Proof> proofs) {
      this(convertToDateTime(recordDate), operation, author, proofs);
   }

   private static DateTime convertToDateTime(Calendar recordDate) {
      DateTime convertedCalendarDate = null;
      if (recordDate != null) {
         convertedCalendarDate = new DateTime(recordDate.getTimeInMillis());
      }

      return convertedCalendarDate;
   }

   public OperationContext(DateTime recordDate, String operation, Author author, List<Proof> proofs) {
      this.operation = operation;
      this.recordDate = recordDate;
      this.author = author;
      this.proofs = proofs;
   }

   public OperationContext() {
   }

   public String getOperation() {
      return this.operation;
   }

   public void setOperation(String operation) {
      this.operation = operation;
   }

   public DateTime getRecordDateTime() {
      return this.recordDate;
   }

   public void setRecordDateTime(DateTime recordDate) {
      this.recordDate = recordDate;
   }

   /** @deprecated */
   @Deprecated
   public Calendar getRecordDate() {
      return this.recordDate.toGregorianCalendar();
   }

   /** @deprecated */
   @Deprecated
   public void setRecordDate(Calendar recordDate) {
      this.recordDate = new DateTime(recordDate);
   }

   public Author getAuthor() {
      return this.author;
   }

   public void setAuthor(Author author) {
      this.author = author;
   }

   public List<Proof> getProofs() {
      return this.proofs;
   }

   public void setProofs(List<Proof> proofs) {
      this.proofs = proofs;
   }

   public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();
      builder.append(this.operation);
      builder.append(this.author);
      builder.append(this.proofs);
      builder.append(this.recordDate);
      return builder.toHashCode();
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof OperationContext)) {
         return false;
      } else if (obj == this) {
         return true;
      } else {
         OperationContext other = (OperationContext)obj;
         EqualsBuilder builder = new EqualsBuilder();
         builder.append(this.operation, other.operation);
         builder.append(this.author, other.author);
         builder.append(this.proofs, other.proofs);
         builder.append(this.recordDate, other.recordDate);
         return builder.isEquals();
      }
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append(this.operation);
      builder.append(this.author);
      builder.append(this.proofs);
      builder.append(this.recordDate);
      return builder.toString();
   }

   public static class Builder {
      private OperationContext operationContext = new OperationContext();

      public OperationContext.Builder withOperation(String operation) {
         this.operationContext.setOperation(operation);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public OperationContext.Builder withRecordDate(Calendar recordDate) {
         this.operationContext.setRecordDateTime(new DateTime(recordDate));
         return this;
      }

      public OperationContext.Builder withRecordDateTime(DateTime recordDate) {
         this.operationContext.setRecordDateTime(recordDate);
         return this;
      }

      public OperationContext.Builder withAuthor(Author author) {
         this.operationContext.setAuthor(author);
         return this;
      }

      public OperationContext.Builder addProof(Proof proof) {
         this.operationContext.getProofs().add(proof);
         return this;
      }

      public OperationContext build() {
         return this.operationContext;
      }
   }
}
