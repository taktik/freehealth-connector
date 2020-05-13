package be.apb.gfddpp.common.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PharmacyData"
)
public class PharmacyData {
   @XmlElement(
      name = "id"
   )
   private String id;
   @XmlElement(
      name = "pharmacyid"
   )
   private String pharmacyid;

   public PharmacyData() {
   }

   public PharmacyData(String id, String pharmacyid) {
      this.id = id;
      this.pharmacyid = pharmacyid;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPharmacyid() {
      return this.pharmacyid;
   }

   public void setPharmacyid(String pharmacyid) {
      this.pharmacyid = pharmacyid;
   }
}
