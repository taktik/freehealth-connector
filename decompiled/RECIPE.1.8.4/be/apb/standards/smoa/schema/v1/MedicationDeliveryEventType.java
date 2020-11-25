package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.AbstractPharmacyType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicationDeliveryEventType",
   propOrder = {"abstractPharmacy", "deliveries"}
)
public class MedicationDeliveryEventType extends AbstractEventType {
   @XmlElementRef(
      name = "abstract-Pharmacy",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractPharmacyType> abstractPharmacy;
   protected DeliveriesType deliveries;

   public JAXBElement<? extends AbstractPharmacyType> getAbstractPharmacy() {
      return this.abstractPharmacy;
   }

   public void setAbstractPharmacy(JAXBElement<? extends AbstractPharmacyType> value) {
      this.abstractPharmacy = value;
   }

   public DeliveriesType getDeliveries() {
      return this.deliveries;
   }

   public void setDeliveries(DeliveriesType value) {
      this.deliveries = value;
   }
}
