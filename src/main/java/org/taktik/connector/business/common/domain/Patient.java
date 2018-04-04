package org.taktik.connector.business.common.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;

public class Patient implements Serializable {
   private static final long serialVersionUID = -1007270832210514388L;
   private String inss;
   private String regNrWithMut;
   private String mutuality;
   private String firstName;
   private String lastName;
   private String middleName;
   private String eidCardNumber;
   private String sisCardNumber;
   private String isiCardNumber;

   public String getInss() {
      return this.inss;
   }

   public void setInss(String inss) {
      this.inss = inss;
   }

   public String getRegNrWithMut() {
      return this.regNrWithMut;
   }

   public void setRegNrWithMut(String regNrWithMut) {
      this.regNrWithMut = regNrWithMut;
   }

   public String getMutuality() {
      return this.mutuality;
   }

   public void setMutuality(String mutuality) {
      this.mutuality = mutuality;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getMiddleName() {
      return this.middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getEidCardNumber() {
      return this.eidCardNumber;
   }

   public void setEidCardNumber(String eidCardNumber) {
      this.eidCardNumber = eidCardNumber;
   }

   public String getSisCardNumber() {
      return this.sisCardNumber;
   }

   public void setSisCardNumber(String sisCardNumber) {
      this.sisCardNumber = sisCardNumber;
   }

   public String getIsiCardNumber() {
      return this.isiCardNumber;
   }

   public void setIsiCardNumber(String isiCardNumber) {
      this.isiCardNumber = isiCardNumber;
   }

   public int hashCode() {
      int result = 1;
      result = 31 * result + (this.eidCardNumber == null ? 0 : this.eidCardNumber.hashCode());
      result = 31 * result + (this.firstName == null ? 0 : this.firstName.hashCode());
      result = 31 * result + (this.inss == null ? 0 : this.inss.hashCode());
      result = 31 * result + (this.isiCardNumber == null ? 0 : this.isiCardNumber.hashCode());
      result = 31 * result + (this.lastName == null ? 0 : this.lastName.hashCode());
      result = 31 * result + (this.middleName == null ? 0 : this.middleName.hashCode());
      result = 31 * result + (this.mutuality == null ? 0 : this.mutuality.hashCode());
      result = 31 * result + (this.regNrWithMut == null ? 0 : this.regNrWithMut.hashCode());
      result = 31 * result + (this.sisCardNumber == null ? 0 : this.sisCardNumber.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Patient other = (Patient)obj;
         return (new EqualsBuilder()).append(this.eidCardNumber, other.eidCardNumber).append(this.firstName, other.firstName).append(this.inss, other.inss).append(this.isiCardNumber, other.isiCardNumber).append(this.lastName, other.lastName).append(this.middleName, other.middleName).append(this.mutuality, other.mutuality).append(this.regNrWithMut, other.regNrWithMut).append(this.sisCardNumber, other.sisCardNumber).isEquals();
      }
   }

   public String toString() {
      return "Patient [inss=" + this.inss + ", regNrWithMut=" + this.regNrWithMut + ", mutuality=" + this.mutuality + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", middleName=" + this.middleName + ", eidCardNumber=" + this.eidCardNumber + ", sisCardNumber=" + this.sisCardNumber + ", isiCardNumber=" + this.isiCardNumber + "]";
   }

   public static class Builder {
      private Patient patient;

      public Builder() {
         this.patient = new Patient();
      }

      public Builder(Patient existingPatient) {
         this.patient = existingPatient;
      }

      public Patient.Builder withFirstName(String value) {
         this.patient.setFirstName(value);
         return this;
      }

      public Patient.Builder withFamilyName(String value) {
         this.patient.setLastName(value);
         return this;
      }

      public Patient.Builder withLastName(String value) {
         this.patient.setLastName(value);
         return this;
      }

      public Patient.Builder withMiddleName(String value) {
         this.patient.setMiddleName(value);
         return this;
      }

      public Patient.Builder withMutuality(String value) {
         this.patient.setMutuality(value);
         return this;
      }

      public Patient.Builder withRegNrWithMut(String value) {
         this.patient.setRegNrWithMut(value);
         return this;
      }

      public Patient.Builder withInss(String value) {
         this.patient.setInss(value);
         return this;
      }

      public Patient.Builder withSis(String value) {
         this.patient.setSisCardNumber(value);
         return this;
      }

      public Patient.Builder withIsiPlus(String value) {
         this.patient.setIsiCardNumber(value);
         return this;
      }

      public Patient.Builder withEid(String eid) {
         this.patient.setEidCardNumber(eid);
         return this;
      }

      public Patient build() throws IllegalStateException {
         boolean hasName = this.patient.getFirstName() == null && this.patient.getLastName() == null;
         boolean hasCompleteName = this.patient.getFirstName() != null && this.patient.getLastName() != null;
         if (!hasName && !hasCompleteName) {
            throw new IllegalStateException("Patient should have a firstName and a FamilyName, (X)OR a name and nothing else");
         } else {
            return this.patient;
         }
      }
   }
}
