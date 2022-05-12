package be.fgov.ehealth.mediprima.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PharmaceuticalDrugType",
   propOrder = {"pharmacyList"}
)
public class PharmaceuticalDrugType extends MedicalCoverCommonInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PharmacyList"
   )
   protected NihiiNumberListType pharmacyList;

   public PharmaceuticalDrugType() {
   }

   public NihiiNumberListType getPharmacyList() {
      return this.pharmacyList;
   }

   public void setPharmacyList(NihiiNumberListType value) {
      this.pharmacyList = value;
   }
}
