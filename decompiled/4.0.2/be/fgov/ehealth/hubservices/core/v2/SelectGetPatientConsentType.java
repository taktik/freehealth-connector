package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectGetPatientConsentType",
   propOrder = {"patient", "consent"}
)
public class SelectGetPatientConsentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   protected BasicConsentType consent;

   public SelectGetPatientConsentType() {
   }

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public BasicConsentType getConsent() {
      return this.consent;
   }

   public void setConsent(BasicConsentType value) {
      this.consent = value;
   }
}
