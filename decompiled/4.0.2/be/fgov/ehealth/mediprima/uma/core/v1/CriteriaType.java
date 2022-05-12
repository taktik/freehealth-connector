package be.fgov.ehealth.mediprima.uma.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CriteriaType",
   propOrder = {"beneficiarySsin", "period", "medicalCover", "attestationNumber"}
)
public class CriteriaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BeneficiarySsin",
      required = true
   )
   protected String beneficiarySsin;
   @XmlElement(
      name = "Period"
   )
   protected PeriodType period;
   @XmlElement(
      name = "MedicalCover"
   )
   protected String medicalCover;
   @XmlElement(
      name = "AttestationNumber"
   )
   protected String attestationNumber;

   public CriteriaType() {
   }

   public String getBeneficiarySsin() {
      return this.beneficiarySsin;
   }

   public void setBeneficiarySsin(String value) {
      this.beneficiarySsin = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
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
