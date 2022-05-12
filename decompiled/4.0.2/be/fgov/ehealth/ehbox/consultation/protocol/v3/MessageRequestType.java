package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MessageRequestType",
   propOrder = {"boxId", "source", "messageId"}
)
public class MessageRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BoxId"
   )
   protected BoxIdType boxId;
   @XmlElement(
      name = "Source",
      required = true,
      defaultValue = "INBOX"
   )
   protected String source;
   @XmlElement(
      name = "MessageId",
      required = true
   )
   protected String messageId;

   public MessageRequestType() {
   }

   public BoxIdType getBoxId() {
      return this.boxId;
   }

   public void setBoxId(BoxIdType value) {
      this.boxId = value;
   }

   public String getSource() {
      return this.source;
   }

   public void setSource(String value) {
      this.source = value;
   }

   public String getMessageId() {
      return this.messageId;
   }

   public void setMessageId(String value) {
      this.messageId = value;
   }
}
