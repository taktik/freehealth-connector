package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ArchivePrescriptionEventType",
   propOrder = {"executorId", "rid", "markAsDeliverTime", "archiveStandardVersion", "archiveStandard", "sguid", "dguid"}
)
public class ArchivePrescriptionEventType extends AbstractEventType {
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
   protected XMLGregorianCalendar markAsDeliverTime;
   @XmlElement(
      required = true
   )
   protected String archiveStandardVersion;
   @XmlElement(
      required = true
   )
   protected byte[] archiveStandard;
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

   public XMLGregorianCalendar getMarkAsDeliverTime() {
      return this.markAsDeliverTime;
   }

   public void setMarkAsDeliverTime(XMLGregorianCalendar value) {
      this.markAsDeliverTime = value;
   }

   public String getArchiveStandardVersion() {
      return this.archiveStandardVersion;
   }

   public void setArchiveStandardVersion(String value) {
      this.archiveStandardVersion = value;
   }

   public byte[] getArchiveStandard() {
      return this.archiveStandard;
   }

   public void setArchiveStandard(byte[] value) {
      this.archiveStandard = value;
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
