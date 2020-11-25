package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "putVisionParam",
   propOrder = {"symmKey", "rid", "vision"}
)
@XmlRootElement(
   name = "putVisionParam"
)
public class PutVisionParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   @XmlElement(
      required = true
   )
   protected String rid;
   @XmlElement(
      required = true
   )
   protected String vision;

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

   public String getVision() {
      return this.vision;
   }

   public void setVision(String value) {
      this.vision = value;
   }

}
