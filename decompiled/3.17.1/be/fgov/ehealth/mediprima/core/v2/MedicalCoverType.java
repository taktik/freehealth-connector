package be.fgov.ehealth.mediprima.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicalCoverType",
   propOrder = {"doctor", "hospitalization", "ambulatoryHospitalization", "medicalTransportation", "miscellaneous", "paramedic", "pharmaceuticalDrug", "prosthesis"}
)
public class MedicalCoverType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Doctor"
   )
   protected DoctorType doctor;
   @XmlElement(
      name = "Hospitalization"
   )
   protected HospitalizationType hospitalization;
   @XmlElement(
      name = "AmbulatoryHospitalization"
   )
   protected HospitalizationType ambulatoryHospitalization;
   @XmlElement(
      name = "MedicalTransportation"
   )
   protected MedicalTransportationType medicalTransportation;
   @XmlElement(
      name = "Miscellaneous"
   )
   protected MiscellaneousType miscellaneous;
   @XmlElement(
      name = "Paramedic"
   )
   protected ParamedicType paramedic;
   @XmlElement(
      name = "PharmaceuticalDrug"
   )
   protected PharmaceuticalDrugType pharmaceuticalDrug;
   @XmlElement(
      name = "Prosthesis"
   )
   protected ProsthesisType prosthesis;

   public DoctorType getDoctor() {
      return this.doctor;
   }

   public void setDoctor(DoctorType value) {
      this.doctor = value;
   }

   public HospitalizationType getHospitalization() {
      return this.hospitalization;
   }

   public void setHospitalization(HospitalizationType value) {
      this.hospitalization = value;
   }

   public HospitalizationType getAmbulatoryHospitalization() {
      return this.ambulatoryHospitalization;
   }

   public void setAmbulatoryHospitalization(HospitalizationType value) {
      this.ambulatoryHospitalization = value;
   }

   public MedicalTransportationType getMedicalTransportation() {
      return this.medicalTransportation;
   }

   public void setMedicalTransportation(MedicalTransportationType value) {
      this.medicalTransportation = value;
   }

   public MiscellaneousType getMiscellaneous() {
      return this.miscellaneous;
   }

   public void setMiscellaneous(MiscellaneousType value) {
      this.miscellaneous = value;
   }

   public ParamedicType getParamedic() {
      return this.paramedic;
   }

   public void setParamedic(ParamedicType value) {
      this.paramedic = value;
   }

   public PharmaceuticalDrugType getPharmaceuticalDrug() {
      return this.pharmaceuticalDrug;
   }

   public void setPharmaceuticalDrug(PharmaceuticalDrugType value) {
      this.pharmaceuticalDrug = value;
   }

   public ProsthesisType getProsthesis() {
      return this.prosthesis;
   }

   public void setProsthesis(ProsthesisType value) {
      this.prosthesis = value;
   }
}
