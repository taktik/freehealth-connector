package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.AcknowledgmentType;
import be.fgov.ehealth.ehbox.core.v3.ErrorType;
import be.fgov.ehealth.ehbox.core.v3.FreeInformationsType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultationContentType",
   propOrder = {"document", "freeInformations", "encryptableINSSPatient", "annices", "acknowledgment", "error"}
)
public class ConsultationContentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Document",
      required = true
   )
   protected ConsultationDocumentType document;
   @XmlElement(
      name = "FreeInformations"
   )
   protected FreeInformationsType freeInformations;
   @XmlElement(
      name = "EncryptableINSSPatient"
   )
   protected byte[] encryptableINSSPatient;
   @XmlElement(
      name = "Annex"
   )
   protected List<ConsultationAnnexType> annices;
   @XmlElement(
      name = "Acknowledgment"
   )
   protected AcknowledgmentType acknowledgment;
   @XmlElement(
      name = "Error"
   )
   protected ErrorType error;

   public ConsultationDocumentType getDocument() {
      return this.document;
   }

   public void setDocument(ConsultationDocumentType value) {
      this.document = value;
   }

   public FreeInformationsType getFreeInformations() {
      return this.freeInformations;
   }

   public void setFreeInformations(FreeInformationsType value) {
      this.freeInformations = value;
   }

   public byte[] getEncryptableINSSPatient() {
      return this.encryptableINSSPatient;
   }

   public void setEncryptableINSSPatient(byte[] value) {
      this.encryptableINSSPatient = value;
   }

   public List<ConsultationAnnexType> getAnnices() {
      if (this.annices == null) {
         this.annices = new ArrayList();
      }

      return this.annices;
   }

   public AcknowledgmentType getAcknowledgment() {
      return this.acknowledgment;
   }

   public void setAcknowledgment(AcknowledgmentType value) {
      this.acknowledgment = value;
   }

   public ErrorType getError() {
      return this.error;
   }

   public void setError(ErrorType value) {
      this.error = value;
   }
}
