package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SealedResponseType",
   propOrder = {"statusType", "singleMessageSealed"}
)
public class SealedResponseType extends ResponseType {
   @XmlElement(
      name = "StatusType",
      required = true
   )
   protected StatusTypeType statusType;
   @XmlElement(
      name = "SingleMessageSealed",
      required = true
   )
   protected byte[] singleMessageSealed;

   public StatusTypeType getStatusType() {
      return this.statusType;
   }

   public void setStatusType(StatusTypeType var1) {
      this.statusType = var1;
   }

   public byte[] getSingleMessageSealed() {
      return this.singleMessageSealed;
   }

   public void setSingleMessageSealed(byte[] var1) {
      this.singleMessageSealed = (byte[])var1;
   }
}
