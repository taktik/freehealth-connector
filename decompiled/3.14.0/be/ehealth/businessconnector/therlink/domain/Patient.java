package be.ehealth.businessconnector.therlink.domain;

import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorExceptionValues;
import java.io.Serializable;

/** @deprecated */
@Deprecated
public class Patient extends be.ehealth.business.common.domain.Patient implements Serializable {
   private static final long serialVersionUID = -1781258533446951948L;
   private String name;

   public Patient() {
   }

   public Patient(String inss) {
      this.setInss(inss);
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSis() {
      return this.getSisCardNumber();
   }

   public void setSis(String sis) {
      this.setSisCardNumber(sis);
   }

   public String getIsiCardNumber() {
      throw new UnsupportedOperationException("this operation won't be supported for therlink specific Patient type : use the be.ehealth.business.common.domain.Patient class instead!");
   }

   public void setIsiCardNumber(String isiCardNumber) {
      throw new UnsupportedOperationException("this operation won't be supported for therlink specific Patient type : use the be.ehealth.business.common.domain.Patient class instead!");
   }

   public int hashCode() {
      int prime = true;
      int result = super.hashCode();
      result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      boolean superEqualsResult = super.equals(obj);
      if (superEqualsResult) {
         if (this == obj) {
            return true;
         }

         if (obj == null) {
            return false;
         }

         if (this.getClass() != obj.getClass()) {
            return false;
         }

         Patient other = (Patient)obj;
         if (this.name == null) {
            if (other.name != null) {
               return false;
            }
         } else if (!this.name.equals(other.name)) {
            return false;
         }
      }

      return superEqualsResult;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Patient ").append("[ inss=").append(this.getInss()).append("]");
      return builder.toString();
   }

   /** @deprecated */
   @Deprecated
   public static class Builder {
      private Patient patient = new Patient();

      /** @deprecated */
      @Deprecated
      public Patient.Builder withFirstName(String value) {
         this.patient.setFirstName(value);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Patient.Builder withFamilyName(String value) {
         this.patient.setLastName(value);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Patient.Builder withName(String value) {
         this.patient.setName(value);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Patient.Builder withInss(String value) {
         this.patient.setInss(value);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Patient.Builder withSis(String value) {
         this.patient.setSis(value);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Patient.Builder withEid(String eid) {
         this.patient.setEidCardNumber(eid);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Patient build() throws TherLinkBusinessConnectorException {
         boolean hasName = this.patient.getFirstName() == null && this.patient.getLastName() == null && this.patient.getName() != null;
         boolean hasCompleteName = this.patient.getFirstName() != null && this.patient.getLastName() != null && this.patient.getName() == null;
         if (!hasName && !hasCompleteName) {
            throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.VALIDATION_ERROR, new Object[]{"Patient should have a firstName and a FamilyName, (X)OR a name and nothing else"});
         } else {
            return this.patient;
         }
      }
   }
}
