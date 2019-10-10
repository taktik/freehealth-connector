package be.fgov.ehealth.technicalconnector.ra.domain;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ForeignerRequest extends Request implements Serializable {
   private static final long serialVersionUID = 1L;
   private Actor foreignPerson;
   private ContactData contactData;
   private byte[] csr;

   public ForeignerRequest(Actor foreignPerson, ContactData contactData, byte[] csr) throws TechnicalConnectorException {
      this.foreignPerson = foreignPerson;
      this.contactData = contactData;
      this.csr = csr;
   }

   public Actor getForeignPerson() {
      return this.foreignPerson;
   }

   public ContactData getContactData() {
      return this.contactData;
   }

   public byte[] getCsr() {
      return this.csr;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ForeignerRequest that = (ForeignerRequest)o;
         return (new EqualsBuilder()).appendSuper(super.equals(o)).append(this.getForeignPerson(), that.getForeignPerson()).append(this.getContactData(), that.getContactData()).append(this.getCsr(), that.getCsr()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).appendSuper(super.hashCode()).append(this.getForeignPerson()).append(this.getContactData()).append(this.getCsr()).toHashCode();
   }
}
