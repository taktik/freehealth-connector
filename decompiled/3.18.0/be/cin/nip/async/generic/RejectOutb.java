package be.cin.nip.async.generic;

import be.cin.mycarenet.esb.common.v2.CommonOutputType;
import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.types.v1.FaultType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"commonOutput", "origin", "fault"}
)
@XmlRootElement(
   name = "RejectOutb"
)
public class RejectOutb implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonOutput",
      required = true
   )
   protected CommonOutputType commonOutput;
   @XmlElement(
      name = "Origin",
      required = true
   )
   protected OrigineType origin;
   @XmlElement(
      name = "Fault",
      required = true
   )
   protected FaultType fault;
   @XmlAttribute(
      name = "msgName",
      required = true
   )
   protected String msgName;

   public CommonOutputType getCommonOutput() {
      return this.commonOutput;
   }

   public void setCommonOutput(CommonOutputType value) {
      this.commonOutput = value;
   }

   public OrigineType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OrigineType value) {
      this.origin = value;
   }

   public FaultType getFault() {
      return this.fault;
   }

   public void setFault(FaultType value) {
      this.fault = value;
   }

   public String getMsgName() {
      return this.msgName;
   }

   public void setMsgName(String value) {
      this.msgName = value;
   }
}
