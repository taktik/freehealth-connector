package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BvacDoctor",
   propOrder = {"identification", "identity"}
)
public class BvacDoctor {
   @XmlElement(
      required = true
   )
   protected IdentificationBvacDoctor identification;
   @XmlElement(
      required = true
   )
   protected IdentityBvac identity;

   public IdentificationBvacDoctor getIdentification() {
      return this.identification;
   }

   public void setIdentification(IdentificationBvacDoctor value) {
      this.identification = value;
   }

   public IdentityBvac getIdentity() {
      return this.identity;
   }

   public void setIdentity(IdentityBvac value) {
      this.identity = value;
   }
}
