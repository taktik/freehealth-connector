package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPrescriptionIdType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KmehrPrescriptionType",
   propOrder = {"id", "kmehrPrescription"}
)
public class KmehrPrescriptionType extends AbstractPrescriptionType {
   @XmlElement(
      required = true
   )
   protected AbstractPrescriptionIdType id;
   protected Kmehrmessage kmehrPrescription;

   public AbstractPrescriptionIdType getId() {
      return this.id;
   }

   public void setId(AbstractPrescriptionIdType value) {
      this.id = value;
   }

   public Kmehrmessage getKmehrPrescription() {
      return this.kmehrPrescription;
   }

   public void setKmehrPrescription(Kmehrmessage value) {
      this.kmehrPrescription = value;
   }
}
