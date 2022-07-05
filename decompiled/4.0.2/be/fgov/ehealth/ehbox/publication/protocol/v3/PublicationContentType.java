package be.fgov.ehealth.ehbox.publication.protocol.v3;

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
   name = "PublicationContentType",
   propOrder = {"document", "freeInformations", "encryptableINSSPatient", "annices"}
)
public class PublicationContentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Document",
      required = true
   )
   protected PublicationDocumentType document;
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
   protected List<PublicationAnnexType> annices;

   public PublicationContentType() {
   }

   public PublicationDocumentType getDocument() {
      return this.document;
   }

   public void setDocument(PublicationDocumentType value) {
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

   public List<PublicationAnnexType> getAnnices() {
      if (this.annices == null) {
         this.annices = new ArrayList();
      }

      return this.annices;
   }
}
