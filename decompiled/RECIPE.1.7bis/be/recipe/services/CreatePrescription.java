package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "createPrescription",
   propOrder = {"createPrescriptionParamSealed", "prescriptionType", "documentId", "keyId", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "createPrescription"
)
public class CreatePrescription implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CreatePrescriptionParamSealed"
   )
   protected byte[] createPrescriptionParamSealed;
   @XmlElement(
      name = "PrescriptionType"
   )
   protected String prescriptionType;
   @XmlElement(
      name = "DocumentId"
   )
   protected String documentId;
   @XmlElement(
      name = "KeyId"
   )
   protected String keyId;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getCreatePrescriptionParamSealed() {
      return this.createPrescriptionParamSealed;
   }

   public void setCreatePrescriptionParamSealed(byte[] value) {
      this.createPrescriptionParamSealed = (byte[])value;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
   }

   public String getDocumentId() {
      return this.documentId;
   }

   public void setDocumentId(String value) {
      this.documentId = value;
   }

   public String getKeyId() {
      return this.keyId;
   }

   public void setKeyId(String value) {
      this.keyId = value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
