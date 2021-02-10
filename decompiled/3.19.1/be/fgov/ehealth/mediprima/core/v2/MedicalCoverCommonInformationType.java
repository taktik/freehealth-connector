package be.fgov.ehealth.mediprima.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicalCoverCommonInformationType",
   propOrder = {"validityPeriod", "pswcSupport", "amountPatientPartMax", "allowedSupplements"}
)
@XmlSeeAlso({DoctorType.class, HospitalizationType.class, MedicalTransportationType.class, MiscellaneousType.class, ParamedicType.class, PharmaceuticalDrugType.class, ProsthesisType.class})
public class MedicalCoverCommonInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ValidityPeriod",
      required = true
   )
   protected PeriodType validityPeriod;
   @XmlElement(
      name = "PswcSupport"
   )
   protected PswcSupportType pswcSupport;
   @XmlElement(
      name = "AmountPatientPartMax"
   )
   protected Integer amountPatientPartMax;
   @XmlElement(
      name = "AllowedSupplements"
   )
   protected AllowedSupplementsType allowedSupplements;

   public PeriodType getValidityPeriod() {
      return this.validityPeriod;
   }

   public void setValidityPeriod(PeriodType value) {
      this.validityPeriod = value;
   }

   public PswcSupportType getPswcSupport() {
      return this.pswcSupport;
   }

   public void setPswcSupport(PswcSupportType value) {
      this.pswcSupport = value;
   }

   public Integer getAmountPatientPartMax() {
      return this.amountPatientPartMax;
   }

   public void setAmountPatientPartMax(Integer value) {
      this.amountPatientPartMax = value;
   }

   public AllowedSupplementsType getAllowedSupplements() {
      return this.allowedSupplements;
   }

   public void setAllowedSupplements(AllowedSupplementsType value) {
      this.allowedSupplements = value;
   }
}
