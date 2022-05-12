package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.ehbox.core.v3.MessageInfoType;
import be.fgov.ehealth.ehbox.core.v3.SenderType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetFullMessageResponseType",
   propOrder = {"sender", "message", "messageInfo"}
)
@XmlRootElement(
   name = "GetFullMessageResponse"
)
public class GetFullMessageResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Sender"
   )
   protected SenderType sender;
   @XmlElement(
      name = "Message"
   )
   protected ConsultationMessageType message;
   @XmlElement(
      name = "MessageInfo"
   )
   protected MessageInfoType messageInfo;

   public GetFullMessageResponse() {
   }

   public SenderType getSender() {
      return this.sender;
   }

   public void setSender(SenderType value) {
      this.sender = value;
   }

   public ConsultationMessageType getMessage() {
      return this.message;
   }

   public void setMessage(ConsultationMessageType value) {
      this.message = value;
   }

   public MessageInfoType getMessageInfo() {
      return this.messageInfo;
   }

   public void setMessageInfo(MessageInfoType value) {
      this.messageInfo = value;
   }
}
