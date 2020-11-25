package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Abstract-MedicinalProductIdType"
)
@XmlSeeAlso({CnkIdType.class, BeRegistrationIdType.class, AtcDddSystemIdType.class})
public abstract class AbstractMedicinalProductIdType {
}
