package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.model.v1.MinSetPatient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContinuedPharmaceuticalCareDossierEvent",
   propOrder = {"minPatient", "pharmacyId", "dguid", "contentDateTime", "contentType", "contentInfo", "contentData", "metaDataList"}
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
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String dguid;
   @XmlElement(
      name = "ContentDateTime",
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar contentDateTime;
   @XmlElement(
      name = "ContentType",
      required = true
   )
   protected String contentType;
   @XmlElement(
      name = "ContentInfo"
   )
   protected InfoListType contentInfo;
   @XmlElement(
      name = "ContentData",
      required = true
   )
   protected byte[] contentData;
   protected MetaDataListType metaDataList;

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

   public String getDguid() {
      return this.dguid;
   }

   public void setDguid(String value) {
      this.dguid = value;
   }

   public XMLGregorianCalendar getContentDateTime() {
      return this.contentDateTime;
   }

   public void setContentDateTime(XMLGregorianCalendar value) {
      this.contentDateTime = value;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String value) {
      this.contentType = value;
   }

   public InfoListType getContentInfo() {
      return this.contentInfo;
   }

   public void setContentInfo(InfoListType value) {
      this.contentInfo = value;
   }

   public byte[] getContentData() {
      return this.contentData;
   }

   public void setContentData(byte[] value) {
      this.contentData = value;
   }

   public MetaDataListType getMetaDataList() {
      return this.metaDataList;
   }

   public void setMetaDataList(MetaDataListType value) {
      this.metaDataList = value;
   }
}
