package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractAttestIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttestType",
   propOrder = {"id", "description"}
)
public class AttestType extends AbstractAttestType {
   @XmlElement(
      required = true
   )
   protected AbstractAttestIdType id;
   protected String description;

   public AbstractAttestIdType getId() {
      return this.id;
   }

   public void setId(AbstractAttestIdType value) {
      this.id = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }
}
