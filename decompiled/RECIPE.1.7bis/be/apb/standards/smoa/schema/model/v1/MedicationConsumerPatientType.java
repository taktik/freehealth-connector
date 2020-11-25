package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicationConsumerPatientType",
   propOrder = {"abstractPerson"}
)
public class MedicationConsumerPatientType extends AbstractMedicationConsumerPatientType {
   @XmlElementRef(
      name = "abstract-Person",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractPersonType> abstractPerson;

   public JAXBElement<? extends AbstractPersonType> getAbstractPerson() {
      return this.abstractPerson;
   }

   public void setAbstractPerson(JAXBElement<? extends AbstractPersonType> value) {
      this.abstractPerson = value;
   }
}
