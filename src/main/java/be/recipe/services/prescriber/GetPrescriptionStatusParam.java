package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionStatusParam",
   propOrder = {"symmKey", "rid"}
)
@XmlRootElement(
   name = "getPrescriptionStatusParam"
)
public class GetPrescriptionStatusParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   @XmlElement(
      required = true
   )
   protected String rid;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }
}
