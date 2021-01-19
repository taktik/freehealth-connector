package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsurabilityResponseDetailType",
   propOrder = {"generalSituation", "payment", "hospitalized", "medicalHouse", "insurabilityList"}
)
public class InsurabilityResponseDetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GeneralSituation"
   )
   protected GeneralSituationType generalSituation;
   @XmlElement(
      name = "Payment",
      required = true
   )
   protected PaymentType payment;
   @XmlElement(
      name = "Hospitalized"
   )
   protected HospitalizedType hospitalized;
   @XmlElement(
      name = "MedicalHouse"
   )
   protected MedicalHouseType medicalHouse;
   @XmlElement(
      name = "InsurabilityList"
   )
   protected InsurabilityListType insurabilityList;

   public GeneralSituationType getGeneralSituation() {
      return this.generalSituation;
   }

   public void setGeneralSituation(GeneralSituationType value) {
      this.generalSituation = value;
   }

   public PaymentType getPayment() {
      return this.payment;
   }

   public void setPayment(PaymentType value) {
      this.payment = value;
   }

   public HospitalizedType getHospitalized() {
      return this.hospitalized;
   }

   public void setHospitalized(HospitalizedType value) {
      this.hospitalized = value;
   }

   public MedicalHouseType getMedicalHouse() {
      return this.medicalHouse;
   }

   public void setMedicalHouse(MedicalHouseType value) {
      this.medicalHouse = value;
   }

   public InsurabilityListType getInsurabilityList() {
      return this.insurabilityList;
   }

   public void setInsurabilityList(InsurabilityListType value) {
      this.insurabilityList = value;
   }
}
