package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPrescriptionIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferencePrescriptionType",
   propOrder = {"prescriptionId"}
)
public class ReferencePrescriptionType extends AbstractPrescriptionType {
   @XmlElement(
      required = true
   )
   protected AbstractPrescriptionIdType prescriptionId;

   public AbstractPrescriptionIdType getPrescriptionId() {
      return this.prescriptionId;
   }

   public void setPrescriptionId(AbstractPrescriptionIdType value) {
      this.prescriptionId = value;
   }
}
