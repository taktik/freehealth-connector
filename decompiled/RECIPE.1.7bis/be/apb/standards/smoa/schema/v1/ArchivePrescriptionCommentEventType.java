package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ArchivePrescriptionCommentEventType",
   propOrder = {"executorId", "rid", "prescriptionCommentTime", "prescriptionCommentVersion", "prescriptionComment", "sguid", "dguid"}
)
public class ArchivePrescriptionCommentEventType extends AbstractEventType {
   @XmlElement(
      required = true
   )
   protected ID executorId;
   @XmlElement(
      required = true
   )
   protected String rid;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar prescriptionCommentTime;
   @XmlElement(
      required = true
   )
   protected String prescriptionCommentVersion;
   @XmlElement(
      required = true
   )
   protected byte[] prescriptionComment;
   @XmlElement(
      required = true
   )
   protected String sguid;
   @XmlElement(
      required = true
   )
   protected String dguid;

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

   public XMLGregorianCalendar getPrescriptionCommentTime() {
      return this.prescriptionCommentTime;
   }

   public void setPrescriptionCommentTime(XMLGregorianCalendar value) {
      this.prescriptionCommentTime = value;
   }

   public String getPrescriptionCommentVersion() {
      return this.prescriptionCommentVersion;
   }

   public void setPrescriptionCommentVersion(String value) {
      this.prescriptionCommentVersion = value;
   }

   public byte[] getPrescriptionComment() {
      return this.prescriptionComment;
   }

   public void setPrescriptionComment(byte[] value) {
      this.prescriptionComment = value;
   }

   public String getSguid() {
      return this.sguid;
   }

   public void setSguid(String value) {
      this.sguid = value;
   }

   public String getDguid() {
      return this.dguid;
   }

   public void setDguid(String value) {
      this.dguid = value;
   }
}
