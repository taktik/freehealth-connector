package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MagistralType",
   propOrder = {"abstractPreparation", "abstractMagistralCertificate"}
)
public class MagistralType {
   @XmlElementRef(
      name = "abstract-Preparation",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractPreparationType> abstractPreparation;
   @XmlElementRef(
      name = "abstract-MagistralCertificate",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected JAXBElement<? extends AbstractMagistralCertificateType> abstractMagistralCertificate;

   public JAXBElement<? extends AbstractPreparationType> getAbstractPreparation() {
      return this.abstractPreparation;
   }

   public void setAbstractPreparation(JAXBElement<? extends AbstractPreparationType> value) {
      this.abstractPreparation = value;
   }

   public JAXBElement<? extends AbstractMagistralCertificateType> getAbstractMagistralCertificate() {
      return this.abstractMagistralCertificate;
   }

   public void setAbstractMagistralCertificate(JAXBElement<? extends AbstractMagistralCertificateType> value) {
      this.abstractMagistralCertificate = value;
   }
}
