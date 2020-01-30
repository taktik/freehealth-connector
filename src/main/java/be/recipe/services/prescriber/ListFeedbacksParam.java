package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacksParam",
   propOrder = {"readFlag", "symmKey"}
)
@XmlRootElement(
   name = "listFeedbacksParam"
)
public class ListFeedbacksParam extends PartyIdentification {
   protected boolean readFlag;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;

   public boolean isReadFlag() {
      return this.readFlag;
   }

   public void setReadFlag(boolean value) {
      this.readFlag = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

}
