package be.fgov.ehealth.recipe.core.v1;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExecutorServiceAdministrativeInformationType",
   propOrder = {"prescriberIdentifier", "patientIdentifier"}
)
public class ExecutorServiceAdministrativeInformationType {
   @XmlElement(
      name = "PrescriberIdentifier",
      required = true
   )
   protected IdentifierType prescriberIdentifier;
   @XmlElement(
      name = "PatientIdentifier",
      required = true
   )
   protected IdentifierType patientIdentifier;

   public IdentifierType getPrescriberIdentifier() {
      return this.prescriberIdentifier;
   }

   public void setPrescriberIdentifier(IdentifierType value) {
      this.prescriberIdentifier = value;
   }

   public IdentifierType getPatientIdentifier() {
      return this.patientIdentifier;
   }

   public void setPatientIdentifier(IdentifierType value) {
      this.patientIdentifier = value;
   }
}
