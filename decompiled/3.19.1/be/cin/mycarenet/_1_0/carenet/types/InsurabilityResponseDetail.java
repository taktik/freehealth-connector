package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsurabilityResponseDetailType",
   propOrder = {"generalSituation", "payment", "hospitalized", "medicalHouse", "insurabilityList"}
)
@XmlRootElement(
   name = "InsurabilityResponseDetail"
)
public class InsurabilityResponseDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GeneralSituation"
   )
   protected GeneralSituation generalSituation;
   @XmlElement(
      name = "Payment",
      required = true
   )
   protected Payment payment;
   @XmlElement(
      name = "Hospitalized"
   )
   protected Hospitalized hospitalized;
   @XmlElement(
      name = "MedicalHouse"
   )
   protected MedicalHouseType medicalHouse;
   @XmlElement(
      name = "InsurabilityList"
   )
   protected InsurabilityList insurabilityList;

   public GeneralSituation getGeneralSituation() {
      return this.generalSituation;
   }

   public void setGeneralSituation(GeneralSituation value) {
      this.generalSituation = value;
   }

   public Payment getPayment() {
      return this.payment;
   }

   public void setPayment(Payment value) {
      this.payment = value;
   }

   public Hospitalized getHospitalized() {
      return this.hospitalized;
   }

   public void setHospitalized(Hospitalized value) {
      this.hospitalized = value;
   }

   public MedicalHouseType getMedicalHouse() {
      return this.medicalHouse;
   }

   public void setMedicalHouse(MedicalHouseType value) {
      this.medicalHouse = value;
   }

   public InsurabilityList getInsurabilityList() {
      return this.insurabilityList;
   }

   public void setInsurabilityList(InsurabilityList value) {
      this.insurabilityList = value;
   }
}
