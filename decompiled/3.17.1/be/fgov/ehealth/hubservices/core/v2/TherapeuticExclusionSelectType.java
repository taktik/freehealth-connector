package be.fgov.ehealth.hubservices.core.v2;

import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapeuticExclusionSelectType",
   propOrder = {"patient", "hcparty"}
)
@XmlSeeAlso({TherapeuticExclusionHistorySelectType.class})
public class TherapeuticExclusionSelectType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   protected HcpartyType hcparty;

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public HcpartyType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HcpartyType value) {
      this.hcparty = value;
   }
}
