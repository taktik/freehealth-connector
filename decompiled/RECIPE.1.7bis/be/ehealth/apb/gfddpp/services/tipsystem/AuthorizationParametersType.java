package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthorizationParametersType",
   namespace = "urn:be:fgov:ehealth:gfddpp:core:v1",
   propOrder = {"patientConsent", "therapeuticalRelationShip"}
)
public class AuthorizationParametersType {
   @XmlElement(
      name = "PatientConsent"
   )
   protected PatientConsentType patientConsent;
   @XmlElement(
      name = "TherapeuticalRelationShip"
   )
   protected TherapeuticalRelationShipType therapeuticalRelationShip;

   public PatientConsentType getPatientConsent() {
      return this.patientConsent;
   }

   public void setPatientConsent(PatientConsentType var1) {
      this.patientConsent = var1;
   }

   public TherapeuticalRelationShipType getTherapeuticalRelationShip() {
      return this.therapeuticalRelationShip;
   }

   public void setTherapeuticalRelationShip(TherapeuticalRelationShipType var1) {
      this.therapeuticalRelationShip = var1;
   }
}
