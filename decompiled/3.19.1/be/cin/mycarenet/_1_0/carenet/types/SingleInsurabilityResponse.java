package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SingleInsurabilityResponseType",
   propOrder = {"careReceiverDetail", "insurabilityResponseDetail", "careReceiverId", "messageFault", "insurabilityRequestDetail"}
)
@XmlRootElement(
   name = "SingleInsurabilityResponse"
)
public class SingleInsurabilityResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CareReceiverDetail"
   )
   protected CareReceiverDetailType careReceiverDetail;
   @XmlElement(
      name = "InsurabilityResponseDetail"
   )
   protected InsurabilityResponseDetail insurabilityResponseDetail;
   @XmlElement(
      name = "CareReceiverId"
   )
   protected CareReceiverId careReceiverId;
   @XmlElement(
      name = "MessageFault"
   )
   protected MessageFaultType messageFault;
   @XmlElement(
      name = "InsurabilityRequestDetail",
      required = true
   )
   protected InsurabilityRequestDetail insurabilityRequestDetail;
   @XmlAttribute(
      name = "MessageName",
      required = true
   )
   protected MessageNameType messageName;
   @XmlAttribute(
      name = "Version",
      required = true
   )
   protected String version;
   @XmlAttribute(
      name = "Duplicate",
      required = true
   )
   protected boolean duplicate;
   @XmlAttribute(
      name = "TestFlag",
      required = true
   )
   protected boolean testFlag;
   @XmlAttribute(
      name = "SenderReference",
      required = true
   )
   protected String senderReference;
   @XmlAttribute(
      name = "ReceiverReference"
   )
   protected String receiverReference;
   @XmlAttribute(
      name = "Synchronous",
      required = true
   )
   protected boolean synchronous;

   public CareReceiverDetailType getCareReceiverDetail() {
      return this.careReceiverDetail;
   }

   public void setCareReceiverDetail(CareReceiverDetailType value) {
      this.careReceiverDetail = value;
   }

   public InsurabilityResponseDetail getInsurabilityResponseDetail() {
      return this.insurabilityResponseDetail;
   }

   public void setInsurabilityResponseDetail(InsurabilityResponseDetail value) {
      this.insurabilityResponseDetail = value;
   }

   public CareReceiverId getCareReceiverId() {
      return this.careReceiverId;
   }

   public void setCareReceiverId(CareReceiverId value) {
      this.careReceiverId = value;
   }

   public MessageFaultType getMessageFault() {
      return this.messageFault;
   }

   public void setMessageFault(MessageFaultType value) {
      this.messageFault = value;
   }

   public InsurabilityRequestDetail getInsurabilityRequestDetail() {
      return this.insurabilityRequestDetail;
   }

   public void setInsurabilityRequestDetail(InsurabilityRequestDetail value) {
      this.insurabilityRequestDetail = value;
   }

   public MessageNameType getMessageName() {
      return this.messageName;
   }

   public void setMessageName(MessageNameType value) {
      this.messageName = value;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }

   public boolean isDuplicate() {
      return this.duplicate;
   }

   public void setDuplicate(boolean value) {
      this.duplicate = value;
   }

   public boolean isTestFlag() {
      return this.testFlag;
   }

   public void setTestFlag(boolean value) {
      this.testFlag = value;
   }

   public String getSenderReference() {
      return this.senderReference;
   }

   public void setSenderReference(String value) {
      this.senderReference = value;
   }

   public String getReceiverReference() {
      return this.receiverReference;
   }

   public void setReceiverReference(String value) {
      this.receiverReference = value;
   }

   public boolean isSynchronous() {
      return this.synchronous;
   }

   public void setSynchronous(boolean value) {
      this.synchronous = value;
   }
}
