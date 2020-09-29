package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractMedicationConsumerType"
)
@XmlSeeAlso({AbstractMedicationConsumerPatientType.class, AbstractMedicationConsumerAnimalOwnerType.class, AbstractMedicationConsumerUrgentieTrousseType.class, AbstractMedicationConsumerPatientGroupType.class})
public abstract class AbstractMedicationConsumerType extends AbstractEntityType {
}
