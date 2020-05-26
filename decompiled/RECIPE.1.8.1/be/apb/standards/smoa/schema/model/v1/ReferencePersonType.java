package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPersonIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferencePersonType",
   propOrder = {"personId"}
)
public class ReferencePersonType extends AbstractPersonType {
   @XmlElement(
      required = true
   )
   protected AbstractPersonIdType personId;

   public AbstractPersonIdType getPersonId() {
      return this.personId;
   }

   public void setPersonId(AbstractPersonIdType value) {
      this.personId = value;
   }
}
