package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "updateFeedbackFlagParam",
   propOrder = {"rid", "allowFeedback", "symmKey"}
)
@XmlRootElement(
   name = "updateFeedbackFlagParam"
)
public class UpdateFeedbackFlagParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected String rid;
   protected boolean allowFeedback;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public boolean isAllowFeedback() {
      return this.allowFeedback;
   }

   public void setAllowFeedback(boolean value) {
      this.allowFeedback = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

}
