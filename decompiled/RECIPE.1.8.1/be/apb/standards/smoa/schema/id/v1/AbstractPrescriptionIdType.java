package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Abstract-PrescriptionIdType"
)
@XmlSeeAlso({LocalPrescriptionIdType.class, RecipeIdType.class})
public abstract class AbstractPrescriptionIdType {
}
