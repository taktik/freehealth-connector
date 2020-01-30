package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractAttestIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferenceAttestType",
   propOrder = {"attestId"}
)
public class ReferenceAttestType extends AbstractAttestType {
   @XmlElement(
      required = true
   )
   protected AbstractAttestIdType attestId;

   public AbstractAttestIdType getAttestId() {
      return this.attestId;
   }

   public void setAttestId(AbstractAttestIdType value) {
      this.attestId = value;
   }
}
