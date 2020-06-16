package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractSpecialPrescriptionType"
)
@XmlSeeAlso({SpecialPrescriptionType.class})
public abstract class AbstractSpecialPrescriptionType extends AbstractHumanPrescriptionType {
}
