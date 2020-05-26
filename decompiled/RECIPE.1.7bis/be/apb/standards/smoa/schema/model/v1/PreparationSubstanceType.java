package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.QuantityType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PreparationSubstanceType",
   propOrder = {"abstractRawMaterial", "dose"}
)
public class PreparationSubstanceType {
   @XmlElementRef(
      name = "abstract-RawMaterial",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractRawMaterialType> abstractRawMaterial;
   @XmlElement(
      required = true
   )
   protected QuantityType dose;

   public JAXBElement<? extends AbstractRawMaterialType> getAbstractRawMaterial() {
      return this.abstractRawMaterial;
   }

   public void setAbstractRawMaterial(JAXBElement<? extends AbstractRawMaterialType> value) {
      this.abstractRawMaterial = value;
   }

   public QuantityType getDose() {
      return this.dose;
   }

   public void setDose(QuantityType value) {
      this.dose = value;
   }
}
