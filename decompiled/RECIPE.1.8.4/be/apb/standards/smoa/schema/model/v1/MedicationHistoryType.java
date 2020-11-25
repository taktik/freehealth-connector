package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.v1.MetaDataListType;
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
   name = "medicationHistoryType",
   propOrder = {"sessionID", "deliveryDate", "pharmacyId", "minPatient", "encryptedContent", "product", "metaDataList", "tipQualityIndicator"}
)
public class MedicationHistoryType extends AbstractDeliveredMedicationHumanHistoryType {
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String sessionID;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar deliveryDate;
   protected NihiiIdType pharmacyId;
   @XmlElement(
      name = "min-Patient",
      required = true
   )
   protected MinSetPatient minPatient;
   protected EncryptedContentType encryptedContent;
   protected HistoryProductType product;
   protected MetaDataListType metaDataList;
   protected int tipQualityIndicator;

   public String getSessionID() {
      return this.sessionID;
   }

   public void setSessionID(String value) {
      this.sessionID = value;
   }

   public XMLGregorianCalendar getDeliveryDate() {
      return this.deliveryDate;
   }

   public void setDeliveryDate(XMLGregorianCalendar value) {
      this.deliveryDate = value;
   }

   public NihiiIdType getPharmacyId() {
      return this.pharmacyId;
   }

   public void setPharmacyId(NihiiIdType value) {
      this.pharmacyId = value;
   }

   public MinSetPatient getMinPatient() {
      return this.minPatient;
   }

   public void setMinPatient(MinSetPatient value) {
      this.minPatient = value;
   }

   public EncryptedContentType getEncryptedContent() {
      return this.encryptedContent;
   }

   public void setEncryptedContent(EncryptedContentType value) {
      this.encryptedContent = value;
   }

   public HistoryProductType getProduct() {
      return this.product;
   }

   public void setProduct(HistoryProductType value) {
      this.product = value;
   }

   public MetaDataListType getMetaDataList() {
      return this.metaDataList;
   }

   public void setMetaDataList(MetaDataListType value) {
      this.metaDataList = value;
   }

   public int getTipQualityIndicator() {
      return this.tipQualityIndicator;
   }

   public void setTipQualityIndicator(int value) {
      this.tipQualityIndicator = value;
   }
}
