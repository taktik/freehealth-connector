package be.fgov.ehealth.mediprima.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PswcSupportType",
   propOrder = {"zivAmiPart", "patientPart", "supplement", "convention", "prescription"}
)
public class PswcSupportType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ZivAmiPart",
      required = true
   )
   protected String zivAmiPart;
   @XmlElement(
      name = "PatientPart",
      required = true
   )
   protected String patientPart;
   @XmlElement(
      name = "Supplement",
      required = true
   )
   protected String supplement;
   @XmlElement(
      name = "Convention"
   )
   protected Boolean convention;
   @XmlElement(
      name = "Prescription"
   )
   protected Boolean prescription;

   public PswcSupportType() {
   }

   public String getZivAmiPart() {
      return this.zivAmiPart;
   }

   public void setZivAmiPart(String value) {
      this.zivAmiPart = value;
   }

   public String getPatientPart() {
      return this.patientPart;
   }

   public void setPatientPart(String value) {
      this.patientPart = value;
   }

   public String getSupplement() {
      return this.supplement;
   }

   public void setSupplement(String value) {
      this.supplement = value;
   }

   public Boolean isConvention() {
      return this.convention;
   }

   public void setConvention(Boolean value) {
      this.convention = value;
   }

   public Boolean isPrescription() {
      return this.prescription;
   }

   public void setPrescription(Boolean value) {
      this.prescription = value;
   }
}
