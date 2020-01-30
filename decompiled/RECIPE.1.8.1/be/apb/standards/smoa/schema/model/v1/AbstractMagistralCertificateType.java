package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractMagistralCertificateType"
)
@XmlSeeAlso({MagistralCertificateType.class, ReferenceMagistralCertificateType.class})
public abstract class AbstractMagistralCertificateType extends AbstractEntityType {
}
