package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
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
   name = "ConsultDmppType",
   propOrder = {"price", "cheap", "cheapest", "reimbursable", "additionalFields"}
)
public class ConsultDmppType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Price"
   )
   protected BigDecimal price;
   @XmlElement(
      name = "Cheap"
   )
   protected Boolean cheap;
   @XmlElement(
      name = "Cheapest"
   )
   protected Boolean cheapest;
   @XmlElement(
      name = "Reimbursable"
   )
   protected Boolean reimbursable;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlAttribute(
      name = "DeliveryEnvironment",
      required = true
   )
   protected String deliveryEnvironment;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlAttribute(
      name = "CodeType",
      required = true
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

   public ConsultDmppType() {
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal value) {
      this.price = value;
   }

   public Boolean isCheap() {
      return this.cheap;
   }

   public void setCheap(Boolean value) {
      this.cheap = value;
   }

   public Boolean isCheapest() {
      return this.cheapest;
   }

   public void setCheapest(Boolean value) {
      this.cheapest = value;
   }

   public Boolean isReimbursable() {
      return this.reimbursable;
   }

   public void setReimbursable(Boolean value) {
      this.reimbursable = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public String getDeliveryEnvironment() {
      return this.deliveryEnvironment;
   }

   public void setDeliveryEnvironment(String value) {
      this.deliveryEnvironment = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getCodeType() {
      return this.codeType;
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
