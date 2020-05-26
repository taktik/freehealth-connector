package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractHumanPrescriptionType"
)
@XmlSeeAlso({AbstractPatientPrescriptionType.class, AbstractSpecialPrescriptionType.class, AbstractDeferredPrescriptionType.class})
public abstract class AbstractHumanPrescriptionType extends AbstractPrescriptionType {
}
