package be.fgov.ehealth.mediprima.uma.core.v1;

import be.fgov.ehealth.commons.core.v2.Author;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttestationType",
   propOrder = {"author", "beneficiarySsin", "validityPeriod", "medicalCover", "attestationNumber"}
)
public class AttestationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Author",
      required = true
   )
   protected Author author;
   @XmlElement(
      name = "BeneficiarySsin",
      required = true
   )
   protected String beneficiarySsin;
   @XmlElement(
      name = "ValidityPeriod",
      required = true
   )
   protected PeriodType validityPeriod;
   @XmlElement(
      name = "MedicalCover",
      required = true
   )
   protected String medicalCover;
   @XmlElement(
      name = "AttestationNumber",
      required = true
   )
   protected String attestationNumber;

   public AttestationType() {
   }

   public Author getAuthor() {
      return this.author;
   }

   public void setAuthor(Author value) {
      this.author = value;
   }

   public String getBeneficiarySsin() {
      return this.beneficiarySsin;
   }

   public void setBeneficiarySsin(String value) {
      this.beneficiarySsin = value;
   }

   public PeriodType getValidityPeriod() {
      return this.validityPeriod;
   }

   public void setValidityPeriod(PeriodType value) {
      this.validityPeriod = value;
   }

   public String getMedicalCover() {
      return this.medicalCover;
   }

   public void setMedicalCover(String value) {
      this.medicalCover = value;
   }

   public String getAttestationNumber() {
      return this.attestationNumber;
   }

   public void setAttestationNumber(String value) {
      this.attestationNumber = value;
   }
}
