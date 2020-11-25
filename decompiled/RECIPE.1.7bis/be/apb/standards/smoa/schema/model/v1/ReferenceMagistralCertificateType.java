package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractMagistralCertificateIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferenceMagistralCertificateType",
   propOrder = {"magistralCertificateId"}
)
public class ReferenceMagistralCertificateType extends AbstractMagistralCertificateType {
   @XmlElement(
      required = true
   )
   protected AbstractMagistralCertificateIdType magistralCertificateId;

   public AbstractMagistralCertificateIdType getMagistralCertificateId() {
      return this.magistralCertificateId;
   }

   public void setMagistralCertificateId(AbstractMagistralCertificateIdType value) {
      this.magistralCertificateId = value;
   }
}
