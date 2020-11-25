package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicationConsumerUrgentieTrousseType",
   propOrder = {"abstractCareProvider"}
)
public class MedicationConsumerUrgentieTrousseType extends AbstractMedicationConsumerUrgentieTrousseType {
   @XmlElementRef(
      name = "abstract-CareProvider",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractCareProviderType> abstractCareProvider;

   public JAXBElement<? extends AbstractCareProviderType> getAbstractCareProvider() {
      return this.abstractCareProvider;
   }

   public void setAbstractCareProvider(JAXBElement<? extends AbstractCareProviderType> value) {
      this.abstractCareProvider = value;
   }
}
