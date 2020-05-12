package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractVeterinaryPrescriptionType"
)
@XmlSeeAlso({VeterinaryPrescriptionType.class})
public abstract class AbstractVeterinaryPrescriptionType extends AbstractPrescriptionType {
}
