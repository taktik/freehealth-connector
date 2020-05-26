package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.AbstractDeliveredMedicationType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "deliveriesType",
   propOrder = {"abstractDeliveredMedication"}
)
public class DeliveriesType {
   @XmlElementRef(
      name = "abstract-DeliveredMedication",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected List<JAXBElement<? extends AbstractDeliveredMedicationType>> abstractDeliveredMedication;

   public List<JAXBElement<? extends AbstractDeliveredMedicationType>> getAbstractDeliveredMedication() {
      if (this.abstractDeliveredMedication == null) {
         this.abstractDeliveredMedication = new ArrayList();
      }

      return this.abstractDeliveredMedication;
   }
}
