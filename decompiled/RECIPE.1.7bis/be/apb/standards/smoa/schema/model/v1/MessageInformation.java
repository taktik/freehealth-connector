package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.MessageType;
import be.apb.standards.smoa.schema.v1.TextType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MessageInformation",
   propOrder = {"dateTime", "messageType", "messageSubType", "messageText", "messageDetails"}
)
public class MessageInformation {
   @XmlElement(
      name = "DateTime",
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar dateTime;
   @XmlElement(
      name = "MessageType",
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected MessageType messageType;
   @XmlElement(
      name = "MessageSubType",
      required = true
   )
   protected String messageSubType;
   @XmlElement(
      name = "MessageText"
   )
   protected List<TextType> messageText;
   @XmlElement(
      name = "MessageDetails"
   )
   protected String messageDetails;

   public XMLGregorianCalendar getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(XMLGregorianCalendar value) {
      this.dateTime = value;
   }

   public MessageType getMessageType() {
      return this.messageType;
   }

   public void setMessageType(MessageType value) {
      this.messageType = value;
   }

   public String getMessageSubType() {
      return this.messageSubType;
   }

   public void setMessageSubType(String value) {
      this.messageSubType = value;
   }

   public List<TextType> getMessageText() {
      if (this.messageText == null) {
         this.messageText = new ArrayList();
      }

      return this.messageText;
   }

   public String getMessageDetails() {
      return this.messageDetails;
   }

   public void setMessageDetails(String value) {
      this.messageDetails = value;
   }
}
