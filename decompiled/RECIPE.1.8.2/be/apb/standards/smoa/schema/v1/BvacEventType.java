package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.PharmacyList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BvacEventType",
   propOrder = {"pharmacyList"}
)
public class BvacEventType extends AbstractEventType {
   @XmlElement(
      name = "PharmacyList",
      required = true
   )
   protected PharmacyList pharmacyList;

   public PharmacyList getPharmacyList() {
      return this.pharmacyList;
   }

   public void setPharmacyList(PharmacyList value) {
      this.pharmacyList = value;
   }
}
