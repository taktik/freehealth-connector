package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ContractRequest extends Request implements Serializable {
   private static final long serialVersionUID = 1L;
   private ContactData contactData;
   private Actor signer;
   private CertificateIdentifier certificateIdentifier;

   private ContractRequest(ContractRequest.Builder builder) throws TechnicalConnectorException {
      this.contactData = builder.contactData;
      this.signer = builder.signer;
      this.certificateIdentifier = builder.certificateIdentifier;
   }

   public static ContractRequest.Builder newBuilder() {
      return new ContractRequest.Builder();
   }

   public ContactData getContactData() {
      return this.contactData;
   }

   public Actor getSigner() {
      return this.signer;
   }

   public CertificateIdentifier getCertificateIdentifier() {
      return this.certificateIdentifier;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ContractRequest that = (ContractRequest)o;
         return (new EqualsBuilder()).appendSuper(super.equals(o)).append(this.getContactData(), that.getContactData()).append(this.getSigner(), that.getSigner()).append(this.getCertificateIdentifier(), that.getCertificateIdentifier()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).appendSuper(super.hashCode()).append(this.getContactData()).append(this.getSigner()).append(this.getCertificateIdentifier()).toHashCode();
   }

   // $FF: synthetic method
   ContractRequest(ContractRequest.Builder x0, Object x1) throws TechnicalConnectorException {
      this(x0);
   }

   public static final class Builder {
      private ContactData contactData;
      private Actor signer;
      private CertificateIdentifier certificateIdentifier;

      private Builder() {
      }

      public ContractRequest.Builder contactData(ContactData contactData) {
         this.contactData = contactData;
         return this;
      }

      public ContractRequest.Builder signer(Actor signer) {
         this.signer = signer;
         return this;
      }

      public ContractRequest.Builder certificateIdentifier(CertificateIdentifier certificateIdentifier) {
         this.certificateIdentifier = certificateIdentifier;
         return this;
      }

      public ContractRequest build() throws TechnicalConnectorException {
         return new ContractRequest(this);
      }

      // $FF: synthetic method
      Builder(Object x0) {
         this();
      }
   }
}
