package be.fgov.ehealth.hubservices.core.v2;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDTHERAPEUTICLINK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HasTherapeuticLinkSelectType",
   propOrder = {"patient", "hcparty", "cds"}
)
public class HasTherapeuticLinkSelectType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   @XmlElement(
      required = true
   )
   protected HCPartyIdType hcparty;
   @XmlElement(
      name = "cd"
   )
   protected List<CDTHERAPEUTICLINK> cds;

   public HasTherapeuticLinkSelectType() {
   }

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public HCPartyIdType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HCPartyIdType value) {
      this.hcparty = value;
   }

   public List<CDTHERAPEUTICLINK> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }
}
