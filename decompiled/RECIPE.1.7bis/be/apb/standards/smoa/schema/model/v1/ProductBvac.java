package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "productBvac",
   propOrder = {"identification", "description", "prescriptionDate", "quantity", "amounts"}
)
public class ProductBvac {
   @XmlElement(
      required = true
   )
   protected IdentificationBvacProduct identification;
   @XmlElement(
      required = true
   )
   protected DescriptionBvacProduct description;
   @XmlElement(
      defaultValue = "0001-01-01"
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar prescriptionDate;
   @XmlElement(
      required = true
   )
   protected String quantity;
   @XmlElement(
      required = true
   )
   protected AmountsBvacProduct amounts;

   public IdentificationBvacProduct getIdentification() {
      return this.identification;
   }

   public void setIdentification(IdentificationBvacProduct value) {
      this.identification = value;
   }

   public DescriptionBvacProduct getDescription() {
      return this.description;
   }

   public void setDescription(DescriptionBvacProduct value) {
      this.description = value;
   }

   public XMLGregorianCalendar getPrescriptionDate() {
      return this.prescriptionDate;
   }

   public void setPrescriptionDate(XMLGregorianCalendar value) {
      this.prescriptionDate = value;
   }

   public String getQuantity() {
      return this.quantity;
   }

   public void setQuantity(String value) {
      this.quantity = value;
   }

   public AmountsBvacProduct getAmounts() {
      return this.amounts;
   }

   public void setAmounts(AmountsBvacProduct value) {
      this.amounts = value;
   }
}
