package be.ehealth.businessconnector.therlink.domain.requests;

import java.io.Serializable;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.bouncycastle.util.Arrays;

public class BinaryProof implements Serializable {
   private static final long serialVersionUID = 1L;
   private String method;
   private byte[] binary;

   public BinaryProof(String method, byte[] binary) {
      this.method = method;
      this.binary = ArrayUtils.clone(binary);
   }

   public String getMethod() {
      return this.method;
   }

   public void setMethod(String method) {
      this.method = method;
   }

   public byte[] getBinary() {
      return Arrays.clone(this.binary);
   }

   public void setBinary(byte[] binary) {
      this.binary = ArrayUtils.clone(binary);
   }

   public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();
      builder.append(this.binary);
      builder.append(this.method);
      return builder.toHashCode();
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof BinaryProof)) {
         return false;
      } else if (obj == this) {
         return true;
      } else {
         BinaryProof other = (BinaryProof)obj;
         EqualsBuilder builder = new EqualsBuilder();
         builder.append(this.binary, other.binary);
         builder.append(this.method, other.method);
         return builder.isEquals();
      }
   }

   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append(this.binary);
      builder.append(this.method);
      return builder.toString();
   }
}
