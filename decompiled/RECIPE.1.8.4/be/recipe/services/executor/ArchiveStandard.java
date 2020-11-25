package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"creationDate", "executorId", "rid", "timestampedPrescription", "timestampedPrescriptionVersion", "prescriptionSealed", "timestampeId", "prescriptionType", "patientId", "prescriberId", "encryptionKeyId", "encryptionKey", "unsealedPrescription", "prescriptionPrescriberId", "properties", "validationWarnings"}
)
@XmlRootElement(
   name = "archiveStandard"
)
public class ArchiveStandard {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar creationDate;
   @XmlElement(
      required = true
   )
   protected ID executorId;
   @XmlElement(
      required = true
   )
   protected String rid;
   protected byte[] timestampedPrescription;
   protected String timestampedPrescriptionVersion;
   @XmlElement(
      required = true
   )
   protected byte[] prescriptionSealed;
   protected String timestampeId;
   @XmlElement(
      required = true
   )
   protected String prescriptionType;
   @XmlElement(
      required = true
   )
   protected ID patientId;
   @XmlElement(
      required = true
   )
   protected ID prescriberId;
   @XmlElement(
      required = true
   )
   protected String encryptionKeyId;
   @XmlElement(
      required = true
   )
   protected byte[] encryptionKey;
   protected byte[] unsealedPrescription;
   @XmlElement(
      required = true
   )
   protected ID prescriptionPrescriberId;
   protected Properties properties;
   protected ValidationWarnings validationWarnings;

   public XMLGregorianCalendar getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(XMLGregorianCalendar value) {
      this.creationDate = value;
   }

   public ID getExecutorId() {
      return this.executorId;
   }

   public void setExecutorId(ID value) {
      this.executorId = value;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public byte[] getTimestampedPrescription() {
      return this.timestampedPrescription;
   }

   public void setTimestampedPrescription(byte[] value) {
      this.timestampedPrescription = value;
   }

   public String getTimestampedPrescriptionVersion() {
      return this.timestampedPrescriptionVersion;
   }

   public void setTimestampedPrescriptionVersion(String value) {
      this.timestampedPrescriptionVersion = value;
   }

   public byte[] getPrescriptionSealed() {
      return this.prescriptionSealed;
   }

   public void setPrescriptionSealed(byte[] value) {
      this.prescriptionSealed = value;
   }

   public String getTimestampeId() {
      return this.timestampeId;
   }

   public void setTimestampeId(String value) {
      this.timestampeId = value;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
   }

   public ID getPatientId() {
      return this.patientId;
   }

   public void setPatientId(ID value) {
      this.patientId = value;
   }

   public ID getPrescriberId() {
      return this.prescriberId;
   }

   public void setPrescriberId(ID value) {
      this.prescriberId = value;
   }

   public String getEncryptionKeyId() {
      return this.encryptionKeyId;
   }

   public void setEncryptionKeyId(String value) {
      this.encryptionKeyId = value;
   }

   public byte[] getEncryptionKey() {
      return this.encryptionKey;
   }

   public void setEncryptionKey(byte[] value) {
      this.encryptionKey = value;
   }

   public byte[] getUnsealedPrescription() {
      return this.unsealedPrescription;
   }

   public void setUnsealedPrescription(byte[] value) {
      this.unsealedPrescription = value;
   }

   public ID getPrescriptionPrescriberId() {
      return this.prescriptionPrescriberId;
   }

   public void setPrescriptionPrescriberId(ID value) {
      this.prescriptionPrescriberId = value;
   }

   public Properties getProperties() {
      return this.properties;
   }

   public void setProperties(Properties value) {
      this.properties = value;
   }

   public ValidationWarnings getValidationWarnings() {
      return this.validationWarnings;
   }

   public void setValidationWarnings(ValidationWarnings value) {
      this.validationWarnings = value;
   }
}
