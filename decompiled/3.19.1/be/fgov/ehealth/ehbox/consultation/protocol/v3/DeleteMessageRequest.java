package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeleteMessageRequestType",
   propOrder = {"boxId", "source", "messageIds"}
)
@XmlRootElement(
   name = "DeleteMessageRequest"
)
public class DeleteMessageRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BoxId"
   )
   protected BoxIdType boxId;
   @XmlElement(
      name = "Source",
      required = true
   )
   protected String source;
   @XmlElement(
      name = "MessageId",
      required = true
   )
   protected List<String> messageIds;

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

   public List<String> getMessageIds() {
      if (this.messageIds == null) {
         this.messageIds = new ArrayList();
      }

      return this.messageIds;
   }
}
