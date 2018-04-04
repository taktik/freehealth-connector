package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractMagistralCertificateIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MagistralCertificateType",
   propOrder = {"id", "description"}
)
public class MagistralCertificateType extends AbstractMagistralCertificateType {
   @XmlElement(
      required = true
   )
   protected AbstractMagistralCertificateIdType id;
   protected String description;

   public AbstractMagistralCertificateIdType getId() {
      return this.id;
   }

   public void setId(AbstractMagistralCertificateIdType value) {
      this.id = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }
}
