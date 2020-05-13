package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.model.v1.EncryptedContentType;
import be.apb.standards.smoa.schema.model.v1.MinSetPatient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContinuedPharmaceuticalCareDossierEvent",
   propOrder = {"minPatient", "pharmacyId", "encryptedContent"}
)
public class ContinuedPharmaceuticalCareDossierEvent extends AbstractEventType {
   @XmlElement(
      name = "min-Patient",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      required = true
   )
   protected MinSetPatient minPatient;
   @XmlElement(
      required = true
   )
   protected NihiiIdType pharmacyId;
   @XmlElement(
      required = true
   )
   protected EncryptedContentType encryptedContent;

   public MinSetPatient getMinPatient() {
      return this.minPatient;
   }

   public void setMinPatient(MinSetPatient value) {
      this.minPatient = value;
   }

   public NihiiIdType getPharmacyId() {
      return this.pharmacyId;
   }

   public void setPharmacyId(NihiiIdType value) {
      this.pharmacyId = value;
   }

   public EncryptedContentType getEncryptedContent() {
      return this.encryptedContent;
   }

   public void setEncryptedContent(EncryptedContentType value) {
      this.encryptedContent = value;
   }
}
