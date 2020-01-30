package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "createPrescription",
   propOrder = {"createPrescriptionParamSealed", "securityToken", "prescriptionType", "documentId", "keyId", "programIdentification", "prescriptionVersion", "referenceSourceVersion", "mguid"}
)
@XmlRootElement(
   name = "createPrescription"
)
public class CreatePrescription {
   @XmlElement(
      name = "CreatePrescriptionParamSealed"
   )
   protected byte[] createPrescriptionParamSealed;
   @XmlElement(
      name = "SecurityToken"
   )
   protected Object securityToken;
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
      name = "ProgramIdentification",
      required = true
   )
   protected String programIdentification;
   @XmlElement(
      name = "PrescriptionVersion",
      required = true
   )
   protected String prescriptionVersion;
   @XmlElement(
      name = "ReferenceSourceVersion",
      required = true
   )
   protected String referenceSourceVersion;
   @XmlElement(
      name = "Mguid",
      required = true
   )
   protected String mguid;

   public byte[] getCreatePrescriptionParamSealed() {
      return this.createPrescriptionParamSealed;
   }

   public void setCreatePrescriptionParamSealed(byte[] value) {
      this.createPrescriptionParamSealed = value;
   }

   public Object getSecurityToken() {
      return this.securityToken;
   }

   public void setSecurityToken(Object value) {
      this.securityToken = value;
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

   public String getProgramIdentification() {
      return this.programIdentification;
   }

   public void setProgramIdentification(String value) {
      this.programIdentification = value;
   }

   public String getPrescriptionVersion() {
      return this.prescriptionVersion;
   }

   public void setPrescriptionVersion(String value) {
      this.prescriptionVersion = value;
   }

   public String getReferenceSourceVersion() {
      return this.referenceSourceVersion;
   }

   public void setReferenceSourceVersion(String value) {
      this.referenceSourceVersion = value;
   }

   public String getMguid() {
      return this.mguid;
   }

   public void setMguid(String value) {
      this.mguid = value;
   }
}
