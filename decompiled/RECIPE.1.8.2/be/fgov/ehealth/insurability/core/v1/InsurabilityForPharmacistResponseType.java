package be.fgov.ehealth.insurability.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsurabilityForPharmacistResponseType",
   propOrder = {"requested", "careReceiver", "coverage", "verification"}
)
public class InsurabilityForPharmacistResponseType {
   @XmlElement(
      name = "Requested"
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar requested;
   @XmlElement(
      name = "CareReceiver",
      required = true
   )
   protected CareReceiverType careReceiver;
   @XmlElement(
      name = "Coverage"
   )
   protected CoverageType coverage;
   @XmlElement(
      name = "Verification"
   )
   protected VerificationType verification;

   public XMLGregorianCalendar getRequested() {
      return this.requested;
   }

   public void setRequested(XMLGregorianCalendar value) {
      this.requested = value;
   }

   public CareReceiverType getCareReceiver() {
      return this.careReceiver;
   }

   public void setCareReceiver(CareReceiverType value) {
      this.careReceiver = value;
   }

   public CoverageType getCoverage() {
      return this.coverage;
   }

   public void setCoverage(CoverageType value) {
      this.coverage = value;
   }

   public VerificationType getVerification() {
      return this.verification;
   }

   public void setVerification(VerificationType value) {
      this.verification = value;
   }
}
