package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.EncryptedDataType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractDataEntry",
   propOrder = {"dguid", "dataEntryId", "dataEntryVersion", "availabilityStatus", "formatCode", "metaDataList", "businessData", "encryptedData"}
)
@XmlSeeAlso({DataEntryRequest.class, DataEntryResponse.class})
public class AbstractDataEntry {
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String dguid;
   @XmlElement(
      required = true
   )
   protected String dataEntryId;
   protected Integer dataEntryVersion;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected AvailabilityStatus availabilityStatus;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected FormatCode formatCode;
   protected MetaDataListType metaDataList;
   protected byte[] businessData;
   protected EncryptedDataType encryptedData;

   public String getDguid() {
      return this.dguid;
   }

   public void setDguid(String value) {
      this.dguid = value;
   }

   public String getDataEntryId() {
      return this.dataEntryId;
   }

   public void setDataEntryId(String value) {
      this.dataEntryId = value;
   }

   public Integer getDataEntryVersion() {
      return this.dataEntryVersion;
   }

   public void setDataEntryVersion(Integer value) {
      this.dataEntryVersion = value;
   }

   public AvailabilityStatus getAvailabilityStatus() {
      return this.availabilityStatus;
   }

   public void setAvailabilityStatus(AvailabilityStatus value) {
      this.availabilityStatus = value;
   }

   public FormatCode getFormatCode() {
      return this.formatCode;
   }

   public void setFormatCode(FormatCode value) {
      this.formatCode = value;
   }

   public MetaDataListType getMetaDataList() {
      return this.metaDataList;
   }

   public void setMetaDataList(MetaDataListType value) {
      this.metaDataList = value;
   }

   public byte[] getBusinessData() {
      return this.businessData;
   }

   public void setBusinessData(byte[] value) {
      this.businessData = value;
   }

   public EncryptedDataType getEncryptedData() {
      return this.encryptedData;
   }

   public void setEncryptedData(EncryptedDataType value) {
      this.encryptedData = value;
   }
}
