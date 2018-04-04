package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NihiiIdType",
   propOrder = {"nihiiPharmacyNumber"}
)
public class NihiiIdType extends AbstractPharmacyIdType {
   @XmlElement(
      required = true
   )
   protected String nihiiPharmacyNumber;

   public String getNihiiPharmacyNumber() {
      return this.nihiiPharmacyNumber;
   }

   public void setNihiiPharmacyNumber(String value) {
      this.nihiiPharmacyNumber = value;
   }
}
