package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetMessageAcknowledgmentsStatusRequestType",
   propOrder = {"boxId", "messageId", "startIndex", "endIndex"}
)
@XmlRootElement(
   name = "GetMessageAcknowledgmentsStatusRequest"
)
public class GetMessageAcknowledgmentsStatusRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BoxId"
   )
   protected BoxIdType boxId;
   @XmlElement(
      name = "MessageId",
      required = true
   )
   protected String messageId;
   @XmlElement(
      name = "StartIndex",
      defaultValue = "1"
   )
   protected Integer startIndex;
   @XmlElement(
      name = "EndIndex",
      defaultValue = "100"
   )
   protected Integer endIndex;

   public GetMessageAcknowledgmentsStatusRequest() {
   }

   public BoxIdType getBoxId() {
      return this.boxId;
   }

   public void setBoxId(BoxIdType value) {
      this.boxId = value;
   }

   public String getMessageId() {
      return this.messageId;
   }

   public void setMessageId(String value) {
      this.messageId = value;
   }

   public Integer getStartIndex() {
      return this.startIndex;
   }

   public void setStartIndex(Integer value) {
      this.startIndex = value;
   }

   public Integer getEndIndex() {
      return this.endIndex;
   }

   public void setEndIndex(Integer value) {
      this.endIndex = value;
   }
}
