package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LocalPrescriptionIdType",
   propOrder = {"officinaOrderNumber", "officina"}
)
public class LocalPrescriptionIdType extends AbstractPrescriptionIdType {
   protected int officinaOrderNumber;
   @XmlElement(
      required = true
   )
   protected AbstractPharmacyIdType officina;

   public int getOfficinaOrderNumber() {
      return this.officinaOrderNumber;
   }

   public void setOfficinaOrderNumber(int value) {
      this.officinaOrderNumber = value;
   }

   public AbstractPharmacyIdType getOfficina() {
      return this.officina;
   }

   public void setOfficina(AbstractPharmacyIdType value) {
      this.officina = value;
   }
}
