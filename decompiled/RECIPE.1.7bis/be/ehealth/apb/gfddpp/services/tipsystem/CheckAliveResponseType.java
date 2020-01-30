package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CheckAliveResponseType",
   propOrder = {"statusType", "checkAliveResult"}
)
public class CheckAliveResponseType extends ResponseType {
   @XmlElement(
      name = "StatusType",
      required = true
   )
   protected StatusTypeType statusType;
   @XmlElement(
      name = "CheckAliveResult",
      required = true
   )
   protected String checkAliveResult;

   public StatusTypeType getStatusType() {
      return this.statusType;
   }

   public void setStatusType(StatusTypeType var1) {
      this.statusType = var1;
   }

   public String getCheckAliveResult() {
      return this.checkAliveResult;
   }

   public void setCheckAliveResult(String var1) {
      this.checkAliveResult = var1;
   }
}
