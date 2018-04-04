package be.fgov.ehealth.recipe.core.v2;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PrescriberServiceAdministrativeInformationType",
   propOrder = {"patientIdentifier"}
)
@XmlSeeAlso({CreatePrescriptionAdministrativeInformationType.class, SendNotificationAdministrativeInformationType.class})
public class PrescriberServiceAdministrativeInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PatientIdentifier",
      required = true
   )
   protected IdentifierType patientIdentifier;

   public IdentifierType getPatientIdentifier() {
      return this.patientIdentifier;
   }

   public void setPatientIdentifier(IdentifierType value) {
      this.patientIdentifier = value;
   }
}
