package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PatientConsentType",
   namespace = "urn:be:fgov:ehealth:gfddpp:core:v1",
   propOrder = {"patientConsentFlag", "patientSignature"}
)
public class PatientConsentType {
   @XmlElement(
      name = "PatientConsentFlag"
   )
   protected Boolean patientConsentFlag;
   @XmlElement(
      name = "PatientSignature"
   )
   protected PatientSignatureType patientSignature;

   public Boolean isPatientConsentFlag() {
      return this.patientConsentFlag;
   }

   public void setPatientConsentFlag(Boolean var1) {
      this.patientConsentFlag = var1;
   }

   public PatientSignatureType getPatientSignature() {
      return this.patientSignature;
   }

   public void setPatientSignature(PatientSignatureType var1) {
      this.patientSignature = var1;
   }
}
