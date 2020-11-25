package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IncomingMedicationType",
   propOrder = {"abstractMedicinalProduct", "quantity", "incomingDate"}
)
public class IncomingMedicationType extends AbstractIncomingMedicationType {
   @XmlElementRef(
      name = "abstract-MedicinalProduct",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractMedicinalProductType> abstractMedicinalProduct;
   protected int quantity;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar incomingDate;

   public JAXBElement<? extends AbstractMedicinalProductType> getAbstractMedicinalProduct() {
      return this.abstractMedicinalProduct;
   }

   public void setAbstractMedicinalProduct(JAXBElement<? extends AbstractMedicinalProductType> value) {
      this.abstractMedicinalProduct = value;
   }

   public int getQuantity() {
      return this.quantity;
   }

   public void setQuantity(int value) {
      this.quantity = value;
   }

   public XMLGregorianCalendar getIncomingDate() {
      return this.incomingDate;
   }

   public void setIncomingDate(XMLGregorianCalendar value) {
      this.incomingDate = value;
   }
}
