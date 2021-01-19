package be.fgov.ehealth.mediprima.uma.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.AuthorRequestType;
import be.fgov.ehealth.mediprima.uma.core.v1.PeriodType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendUrgentMedicalAidAttestationRequestType",
   propOrder = {"beneficiarySsin", "validityPeriod", "medicalCover"}
)
@XmlRootElement(
   name = "SendUrgentMedicalAidAttestationRequest"
)
public class SendUrgentMedicalAidAttestationRequest extends AuthorRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
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
}
