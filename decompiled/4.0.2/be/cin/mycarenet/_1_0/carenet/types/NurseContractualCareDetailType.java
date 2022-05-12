package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NurseContractualCareDetailType",
   propOrder = {"decisionReference", "referenceProviderModification", "contractualType", "treatmentPeriod", "careDaysByWeek", "visitByDay", "toilets", "katz", "carePlace"}
)
public class NurseContractualCareDetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DecisionReference"
   )
   protected String decisionReference;
   @XmlElement(
      name = "ReferenceProviderModification",
      defaultValue = "false"
   )
   protected Boolean referenceProviderModification;
   @XmlElement(
      name = "ContractualType"
   )
   @XmlSchemaType(
      name = "string"
   )
   protected ContractualTypeType contractualType;
   @XmlElement(
      name = "TreatmentPeriod",
      required = true
   )
   protected PeriodLengthType treatmentPeriod;
   @XmlElement(
      name = "CareDaysByWeek"
   )
   protected String careDaysByWeek;
   @XmlElement(
      name = "VisitByDay"
   )
   protected String visitByDay;
   @XmlElement(
      name = "Toilets"
   )
   protected ToiletsType toilets;
   @XmlElement(
      name = "Katz"
   )
   protected KatzType katz;
   @XmlElement(
      name = "CarePlace"
   )
   protected CarePlaceType carePlace;

   public NurseContractualCareDetailType() {
   }

   public String getDecisionReference() {
      return this.decisionReference;
   }

   public void setDecisionReference(String value) {
      this.decisionReference = value;
   }

   public Boolean isReferenceProviderModification() {
      return this.referenceProviderModification;
   }

   public void setReferenceProviderModification(Boolean value) {
      this.referenceProviderModification = value;
   }

   public ContractualTypeType getContractualType() {
      return this.contractualType;
   }

   public void setContractualType(ContractualTypeType value) {
      this.contractualType = value;
   }

   public PeriodLengthType getTreatmentPeriod() {
      return this.treatmentPeriod;
   }

   public void setTreatmentPeriod(PeriodLengthType value) {
      this.treatmentPeriod = value;
   }

   public String getCareDaysByWeek() {
      return this.careDaysByWeek;
   }

   public void setCareDaysByWeek(String value) {
      this.careDaysByWeek = value;
   }

   public String getVisitByDay() {
      return this.visitByDay;
   }

   public void setVisitByDay(String value) {
      this.visitByDay = value;
   }

   public ToiletsType getToilets() {
      return this.toilets;
   }

   public void setToilets(ToiletsType value) {
      this.toilets = value;
   }

   public KatzType getKatz() {
      return this.katz;
   }

   public void setKatz(KatzType value) {
      this.katz = value;
   }

   public CarePlaceType getCarePlace() {
      return this.carePlace;
   }

   public void setCarePlace(CarePlaceType value) {
      this.carePlace = value;
   }
}
