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
        name = "SingleNurseContractualCareRequestType",
        propOrder = {"careReceiverId", "nurseContractualCareRequestDetail"}
)
@XmlRootElement(
        name = "SingleNurseContractualCareRequest"
)
public class SingleNurseContractualCareRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
           name = "CareReceiverId",
           required = true
   )
   protected ExtCareReceiverStrictIdType careReceiverId;
   @XmlElement(
           name = "NurseContractualCareRequestDetail",
           required = true
   )
   protected NurseContractualCareRequestDetail nurseContractualCareRequestDetail;
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

   public ExtCareReceiverStrictIdType getCareReceiverId() {
      return this.careReceiverId;
   }

   public void setCareReceiverId(ExtCareReceiverStrictIdType value) {
      this.careReceiverId = value;
   }

   public NurseContractualCareRequestDetail getNurseContractualCareRequestDetail() {
      return this.nurseContractualCareRequestDetail;
   }

   public void setNurseContractualCareRequestDetail(NurseContractualCareRequestDetail value) {
      this.nurseContractualCareRequestDetail = value;
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
