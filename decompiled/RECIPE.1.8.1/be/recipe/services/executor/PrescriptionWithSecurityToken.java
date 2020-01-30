package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"creationDate", "rid", "feedbackRequested", "encryptionKeyId", "prescriptionSealed", "prescriptionType", "patientId", "prescriberId", "securityToken", "properties"}
)
@XmlRootElement(
   name = "prescriptionWithSecurityToken"
)
public class PrescriptionWithSecurityToken {
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar creationDate;
   @XmlElement(
      required = true
   )
   protected String rid;
   protected Boolean feedbackRequested;
   protected String encryptionKeyId;
   protected byte[] prescriptionSealed;
   protected String prescriptionType;
   protected ID patientId;
   protected ID prescriberId;
   protected byte[] securityToken;
   protected Properties properties;
   @XmlAttribute(
      name = "id"
   )
   protected String id;

   public XMLGregorianCalendar getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(XMLGregorianCalendar value) {
      this.creationDate = value;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public Boolean isFeedbackRequested() {
      return this.feedbackRequested;
   }

   public void setFeedbackRequested(Boolean value) {
      this.feedbackRequested = value;
   }

   public String getEncryptionKeyId() {
      return this.encryptionKeyId;
   }

   public void setEncryptionKeyId(String value) {
      this.encryptionKeyId = value;
   }

   public byte[] getPrescriptionSealed() {
      return this.prescriptionSealed;
   }

   public void setPrescriptionSealed(byte[] value) {
      this.prescriptionSealed = value;
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

   public byte[] getSecurityToken() {
      return this.securityToken;
   }

   public void setSecurityToken(byte[] value) {
      this.securityToken = value;
   }

   public Properties getProperties() {
      return this.properties;
   }

   public void setProperties(Properties value) {
      this.properties = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
