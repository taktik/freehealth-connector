package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SingleInsurabilityResponseType",
   propOrder = {"careReceiverDetail", "insurabilityResponseDetail", "careReceiverId", "messageFault", "insurabilityRequestDetail"}
)
public class SingleInsurabilityResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CareReceiverDetail"
   )
   protected CareReceiverDetailType careReceiverDetail;
   @XmlElement(
      name = "InsurabilityResponseDetail"
   )
   protected InsurabilityResponseDetailType insurabilityResponseDetail;
   @XmlElement(
      name = "CareReceiverId"
   )
   protected CareReceiverIdType careReceiverId;
   @XmlElement(
      name = "MessageFault"
   )
   protected MessageFaultType messageFault;
   @XmlElement(
      name = "InsurabilityRequestDetail",
      required = true
   )
   protected InsurabilityRequestDetailType insurabilityRequestDetail;
   @XmlAttribute(
      name = "MessageName"
   )
   protected MessageNameType messageName;
   @XmlAttribute(
      name = "Version"
   )
   protected String version;
   @XmlAttribute(
      name = "Duplicate"
   )
   protected Boolean duplicate;
   @XmlAttribute(
      name = "TestFlag"
   )
   protected Boolean testFlag;
   @XmlAttribute(
      name = "SenderReference"
   )
   protected String senderReference;
   @XmlAttribute(
      name = "ReceiverReference"
   )
   protected String receiverReference;
   @XmlAttribute(
      name = "Synchronous"
   )
   protected Boolean synchronous;

   public CareReceiverDetailType getCareReceiverDetail() {
      return this.careReceiverDetail;
   }

   public void setCareReceiverDetail(CareReceiverDetailType value) {
      this.careReceiverDetail = value;
   }

   public InsurabilityResponseDetailType getInsurabilityResponseDetail() {
      return this.insurabilityResponseDetail;
   }

   public void setInsurabilityResponseDetail(InsurabilityResponseDetailType value) {
      this.insurabilityResponseDetail = value;
   }

   public CareReceiverIdType getCareReceiverId() {
      return this.careReceiverId;
   }

   public void setCareReceiverId(CareReceiverIdType value) {
      this.careReceiverId = value;
   }

   public MessageFaultType getMessageFault() {
      return this.messageFault;
   }

   public void setMessageFault(MessageFaultType value) {
      this.messageFault = value;
   }

   public InsurabilityRequestDetailType getInsurabilityRequestDetail() {
      return this.insurabilityRequestDetail;
   }

   public void setInsurabilityRequestDetail(InsurabilityRequestDetailType value) {
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

   public Boolean isDuplicate() {
      return this.duplicate;
   }

   public void setDuplicate(Boolean value) {
      this.duplicate = value;
   }

   public Boolean isTestFlag() {
      return this.testFlag;
   }

   public void setTestFlag(Boolean value) {
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

   public Boolean isSynchronous() {
      return this.synchronous;
   }

   public void setSynchronous(Boolean value) {
      this.synchronous = value;
   }
}
