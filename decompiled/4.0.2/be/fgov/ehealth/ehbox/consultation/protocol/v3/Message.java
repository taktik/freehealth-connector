package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.ContentInfoType;
import be.fgov.ehealth.ehbox.core.v3.ContentSpecificationType;
import be.fgov.ehealth.ehbox.core.v3.CustomMetaType;
import be.fgov.ehealth.ehbox.core.v3.EhboxIdentifierType;
import be.fgov.ehealth.ehbox.core.v3.MessageInfoType;
import be.fgov.ehealth.ehbox.core.v3.SenderType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"messageId", "destination", "sender", "messageInfo", "contentInfo", "contentSpecification", "customMetas"}
)
public class Message implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MessageId",
      required = true
   )
   protected String messageId;
   @XmlElement(
      name = "Destination",
      required = true
   )
   protected EhboxIdentifierType destination;
   @XmlElement(
      name = "Sender",
      required = true
   )
   protected SenderType sender;
   @XmlElement(
      name = "MessageInfo",
      required = true
   )
   protected MessageInfoType messageInfo;
   @XmlElement(
      name = "ContentInfo"
   )
   protected ContentInfoType contentInfo;
   @XmlElement(
      name = "ContentSpecification",
      required = true
   )
   protected ContentSpecificationType contentSpecification;
   @XmlElement(
      name = "CustomMeta"
   )
   protected List<CustomMetaType> customMetas;

   public Message() {
   }

   public String getMessageId() {
      return this.messageId;
   }

   public void setMessageId(String value) {
      this.messageId = value;
   }

   public EhboxIdentifierType getDestination() {
      return this.destination;
   }

   public void setDestination(EhboxIdentifierType value) {
      this.destination = value;
   }

   public SenderType getSender() {
      return this.sender;
   }

   public void setSender(SenderType value) {
      this.sender = value;
   }

   public MessageInfoType getMessageInfo() {
      return this.messageInfo;
   }

   public void setMessageInfo(MessageInfoType value) {
      this.messageInfo = value;
   }

   public ContentInfoType getContentInfo() {
      return this.contentInfo;
   }

   public void setContentInfo(ContentInfoType value) {
      this.contentInfo = value;
   }

   public ContentSpecificationType getContentSpecification() {
      return this.contentSpecification;
   }

   public void setContentSpecification(ContentSpecificationType value) {
      this.contentSpecification = value;
   }

   public List<CustomMetaType> getCustomMetas() {
      if (this.customMetas == null) {
         this.customMetas = new ArrayList();
      }

      return this.customMetas;
   }
}
