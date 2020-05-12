package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractPersonType"
)
@XmlSeeAlso({MaxSetPersonType.class, MaxSetContactPersonType.class, PersonType.class, ReferencePersonType.class})
public abstract class AbstractPersonType extends AbstractEntityType {
}
