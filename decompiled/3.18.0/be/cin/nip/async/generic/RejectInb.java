package be.cin.nip.async.generic;

import be.cin.mycarenet.esb.common.v2.CommonInput;
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
   propOrder = {"commonInput", "fault"}
)
@XmlRootElement(
   name = "RejectInb"
)
public class RejectInb implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonInput",
      required = true
   )
   protected CommonInput commonInput;
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

   public CommonInput getCommonInput() {
      return this.commonInput;
   }

   public void setCommonInput(CommonInput value) {
      this.commonInput = value;
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
