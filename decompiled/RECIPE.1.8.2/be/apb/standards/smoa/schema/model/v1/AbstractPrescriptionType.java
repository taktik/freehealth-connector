package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractPrescriptionType"
)
@XmlSeeAlso({AbstractVeterinaryPrescriptionType.class, KmehrPrescriptionType.class, ReferencePrescriptionType.class, AbstractHumanPrescriptionType.class})
public abstract class AbstractPrescriptionType extends AbstractEntityType {
}
