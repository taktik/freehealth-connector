package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NurseContractualCareResponseDetailType",
   propOrder = {"consultantDoctor", "decision", "treatmentPeriod", "paliatifPatient"}
)
@XmlRootElement(
   name = "NurseContractualCareResponseDetail"
)
public class NurseContractualCareResponseDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ConsultantDoctor"
   )
   protected String consultantDoctor;
   @XmlElement(
      name = "Decision",
      required = true
   )
   protected DecisionType decision;
   @XmlElement(
      name = "TreatmentPeriod"
   )
   protected PeriodType treatmentPeriod;
   @XmlElement(
      name = "PaliatifPatient"
   )
   protected Boolean paliatifPatient;

   public String getConsultantDoctor() {
      return this.consultantDoctor;
   }

   public void setConsultantDoctor(String value) {
      this.consultantDoctor = value;
   }

   public DecisionType getDecision() {
      return this.decision;
   }

   public void setDecision(DecisionType value) {
      this.decision = value;
   }

   public PeriodType getTreatmentPeriod() {
      return this.treatmentPeriod;
   }

   public void setTreatmentPeriod(PeriodType value) {
      this.treatmentPeriod = value;
   }

   public Boolean isPaliatifPatient() {
      return this.paliatifPatient;
   }

   public void setPaliatifPatient(Boolean value) {
      this.paliatifPatient = value;
   }
}
