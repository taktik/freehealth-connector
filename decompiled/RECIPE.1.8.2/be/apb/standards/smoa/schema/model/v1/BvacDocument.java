package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BvacDocument",
   propOrder = {"identification", "currency", "deliveryDate", "patient", "doctor", "productList", "totalAmounts"}
)
public class BvacDocument {
   @XmlElement(
      required = true
   )
   protected IdentificationBvacDocument identification;
   @XmlElement(
      required = true,
      defaultValue = "EUR"
   )
   protected String currency;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar deliveryDate;
   @XmlElement(
      required = true
   )
   protected BvacPatient patient;
   @XmlElement(
      required = true
   )
   protected BvacDoctor doctor;
   @XmlElement(
      required = true
   )
   protected ProductList productList;
   @XmlElement(
      required = true
   )
   protected TotalAmounts totalAmounts;

   public IdentificationBvacDocument getIdentification() {
      return this.identification;
   }

   public void setIdentification(IdentificationBvacDocument value) {
      this.identification = value;
   }

   public String getCurrency() {
      return this.currency;
   }

   public void setCurrency(String value) {
      this.currency = value;
   }

   public XMLGregorianCalendar getDeliveryDate() {
      return this.deliveryDate;
   }

   public void setDeliveryDate(XMLGregorianCalendar value) {
      this.deliveryDate = value;
   }

   public BvacPatient getPatient() {
      return this.patient;
   }

   public void setPatient(BvacPatient value) {
      this.patient = value;
   }

   public BvacDoctor getDoctor() {
      return this.doctor;
   }

   public void setDoctor(BvacDoctor value) {
      this.doctor = value;
   }

   public ProductList getProductList() {
      return this.productList;
   }

   public void setProductList(ProductList value) {
      this.productList = value;
   }

   public TotalAmounts getTotalAmounts() {
      return this.totalAmounts;
   }

   public void setTotalAmounts(TotalAmounts value) {
      this.totalAmounts = value;
   }
}
