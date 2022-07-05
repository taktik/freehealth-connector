package be.fgov.ehealth.ehbox.publication.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"recipients"}
)
@XmlRootElement(
   name = "SendMessageResponse"
)
public class SendMessageResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Recipient"
   )
   protected List<Recipient> recipients;
   @XmlAttribute(
      name = "SentPublicationId"
   )
   protected String sentPublicationId;

   public SendMessageResponse() {
   }

   public List<Recipient> getRecipients() {
      if (this.recipients == null) {
         this.recipients = new ArrayList();
      }

      return this.recipients;
   }

   public String getSentPublicationId() {
      return this.sentPublicationId;
   }

   public void setSentPublicationId(String value) {
      this.sentPublicationId = value;
   }
}
