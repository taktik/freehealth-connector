package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferenceMedicinalProductType",
   propOrder = {"medicinalProductId"}
)
public class ReferenceMedicinalProductType extends AbstractMedicinalProductType {
   @XmlElement(
      required = true
   )
   protected AbstractMedicinalProductIdType medicinalProductId;

   public AbstractMedicinalProductIdType getMedicinalProductId() {
      return this.medicinalProductId;
   }

   public void setMedicinalProductId(AbstractMedicinalProductIdType value) {
      this.medicinalProductId = value;
   }
}
