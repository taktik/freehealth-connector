package be.apb.standards.smoa.schema.v1;

import java.util.ArrayList;
import java.util.List;
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
   name = "MedicationSchemeResponse",
   propOrder = {"sguid", "version", "lastUpdated", "paginationInfo", "dataEntry", "metaData", "updatedBy"}
)
public class MedicationSchemeResponse {
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String sguid;
   protected int version;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar lastUpdated;
   @XmlElement(
      required = true
   )
   protected PaginationInfo paginationInfo;
   protected List<DataEntryResponse> dataEntry;
   protected MetaDataListType metaData;
   @XmlElement(
      required = true
   )
   protected Author updatedBy;

   public String getSguid() {
      return this.sguid;
   }

   public void setSguid(String value) {
      this.sguid = value;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int value) {
      this.version = value;
   }

   public XMLGregorianCalendar getLastUpdated() {
      return this.lastUpdated;
   }

   public void setLastUpdated(XMLGregorianCalendar value) {
      this.lastUpdated = value;
   }

   public PaginationInfo getPaginationInfo() {
      return this.paginationInfo;
   }

   public void setPaginationInfo(PaginationInfo value) {
      this.paginationInfo = value;
   }

   public List<DataEntryResponse> getDataEntry() {
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

   public Author getUpdatedBy() {
      return this.updatedBy;
   }

   public void setUpdatedBy(Author value) {
      this.updatedBy = value;
   }
}
