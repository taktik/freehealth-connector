package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultNonMedicinalProductType",
   propOrder = {"name", "category", "commercialStatus", "producer", "distributor", "additionalFields"}
)
public class ConsultNonMedicinalProductType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "Category",
      required = true
   )
   protected String category;
   @XmlElement(
      name = "CommercialStatus",
      required = true
   )
   protected String commercialStatus;
   @XmlElement(
      name = "Producer",
      required = true
   )
   protected ConsultTextType producer;
   @XmlElement(
      name = "Distributor"
   )
   protected ConsultTextType distributor;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlAttribute(
      name = "code",
      required = true
   )
   protected String code;
   @XmlAttribute(
      name = "codeType"
   )
   protected String codeType;
   @XmlAttribute(
      name = "ProductId",
      required = true
   )
   protected String productId;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultNonMedicinalProductType() {
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public String getCategory() {
      return this.category;
   }

   public void setCategory(String value) {
      this.category = value;
   }

   public String getCommercialStatus() {
      return this.commercialStatus;
   }

   public void setCommercialStatus(String value) {
      this.commercialStatus = value;
   }

   public ConsultTextType getProducer() {
      return this.producer;
   }

   public void setProducer(ConsultTextType value) {
      this.producer = value;
   }

   public ConsultTextType getDistributor() {
      return this.distributor;
   }

   public void setDistributor(ConsultTextType value) {
      this.distributor = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getCodeType() {
      return this.codeType == null ? "CNK" : this.codeType;
   }

   public void setCodeType(String value) {
      this.codeType = value;
   }

   public String getProductId() {
      return this.productId;
   }

   public void setProductId(String value) {
      this.productId = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
