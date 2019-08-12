package be.ehealth.businessconnector.therlink.domain;

import be.ehealth.businessconnector.therlink.domain.requests.BinaryProof;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Proof implements Serializable {
   private static final long serialVersionUID = 1L;
   private String type;
   private BinaryProof binaryProof;

   public Proof(String type) {
      this(type, (BinaryProof)null);
   }

   public Proof(String type, BinaryProof binaryProof) {
      this.type = type;
      this.binaryProof = binaryProof;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public BinaryProof getBinaryProof() {
      return this.binaryProof;
   }

   public void setBinaryProof(BinaryProof binaryProof) {
      this.binaryProof = binaryProof;
   }

   public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();
      builder.append(this.binaryProof);
      builder.append(this.type);
      return builder.toHashCode();
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof Proof)) {
         return false;
      } else if (obj == this) {
         return true;
      } else {
         Proof other = (Proof)obj;
         EqualsBuilder builder = new EqualsBuilder();
         builder.append(this.binaryProof, this.binaryProof);
         builder.append(this.type, other.type);
         return builder.isEquals();
      }
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append(this.binaryProof);
      builder.append(this.type);
      return builder.toString();
   }
}
