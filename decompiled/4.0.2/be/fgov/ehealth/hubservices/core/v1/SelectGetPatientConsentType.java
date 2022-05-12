package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectGetPatientConsentType",
   propOrder = {"patient", "consent"}
)
@XmlRootElement(
   name = "SelectGetPatientConsentType"
)
public class SelectGetPatientConsentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   protected Consent consent;

   public SelectGetPatientConsentType() {
   }

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public Consent getConsent() {
      return this.consent;
   }

   public void setConsent(Consent value) {
      this.consent = value;
   }
}
