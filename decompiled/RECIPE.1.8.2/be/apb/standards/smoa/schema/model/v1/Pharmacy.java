package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Pharmacy",
   propOrder = {"identification", "identity", "bvacList"}
)
public class Pharmacy {
   @XmlElement(
      required = true
   )
   protected Identification identification;
   @XmlElement(
      required = true
   )
   protected Identity identity;
   @XmlElement(
      required = true
   )
   protected BvacList bvacList;

   public Identification getIdentification() {
      return this.identification;
   }

   public void setIdentification(Identification value) {
      this.identification = value;
   }

   public Identity getIdentity() {
      return this.identity;
   }

   public void setIdentity(Identity value) {
      this.identity = value;
   }

   public BvacList getBvacList() {
      return this.bvacList;
   }

   public void setBvacList(BvacList value) {
      this.bvacList = value;
   }
}
