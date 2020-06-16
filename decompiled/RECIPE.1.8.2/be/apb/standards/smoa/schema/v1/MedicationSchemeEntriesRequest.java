package be.apb.standards.smoa.schema.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicationSchemeEntriesRequest",
   propOrder = {"clientMessageId", "subjectId", "version", "currentDateTime", "dataEntry", "metaData"}
)
public class MedicationSchemeEntriesRequest extends AbstractEventType {
   protected String clientMessageId;
   @XmlElement(
      required = true
   )
   protected String subjectId;
   protected Integer version;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar currentDateTime;
   @XmlElement(
      required = true
   )
   protected List<DataEntryRequest> dataEntry;
   protected MetaDataListType metaData;

   public String getClientMessageId() {
      return this.clientMessageId;
   }

   public void setClientMessageId(String value) {
      this.clientMessageId = value;
   }

   public String getSubjectId() {
      return this.subjectId;
   }

   public void setSubjectId(String value) {
      this.subjectId = value;
   }

   public Integer getVersion() {
      return this.version;
   }

   public void setVersion(Integer value) {
      this.version = value;
   }

   public XMLGregorianCalendar getCurrentDateTime() {
      return this.currentDateTime;
   }

   public void setCurrentDateTime(XMLGregorianCalendar value) {
      this.currentDateTime = value;
   }

   public List<DataEntryRequest> getDataEntry() {
      if (this.dataEntry == null) {
         this.dataEntry = new ArrayList();
      }

      return this.dataEntry;
   }

   public MetaDataListType getMetaData() {
      return this.metaData;
   }

   public void setMetaData(MetaDataListType value) {
      this.metaData = value;
   }
}
