package be.fgov.ehealth.ehbox.core.v3;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AcknowledgmentType",
   propOrder = {"messageId", "recipient", "ackType", "dateTime"}
)
public class AcknowledgmentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MessageId",
      required = true
   )
   protected String messageId;
   @XmlElement(
      name = "Recipient"
   )
   protected EhboxIdentifierType recipient;
   @XmlElement(
      name = "AckType",
      required = true
   )
   protected String ackType;
   @XmlElement(
      name = "DateTime",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime dateTime;

   public String getMessageId() {
      return this.messageId;
   }

   public void setMessageId(String value) {
      this.messageId = value;
   }

   public EhboxIdentifierType getRecipient() {
      return this.recipient;
   }

   public void setRecipient(EhboxIdentifierType value) {
      this.recipient = value;
   }

   public String getAckType() {
      return this.ackType;
   }

   public void setAckType(String value) {
      this.ackType = value;
   }

   public DateTime getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(DateTime value) {
      this.dateTime = value;
   }
}
