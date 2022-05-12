package be.fgov.ehealth.rn.commons.business.v1;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
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
   name = "InformationCBSSType",
   propOrder = {"ticketCBSS", "timestampReceive", "timestampReply"}
)
public class InformationCBSSType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TicketCBSS",
      required = true
   )
   protected String ticketCBSS;
   @XmlElement(
      name = "TimestampReceive",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampReceive;
   @XmlElement(
      name = "TimestampReply",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampReply;

   public InformationCBSSType() {
   }

   public String getTicketCBSS() {
      return this.ticketCBSS;
   }

   public void setTicketCBSS(String value) {
      this.ticketCBSS = value;
   }

   public DateTime getTimestampReceive() {
      return this.timestampReceive;
   }

   public void setTimestampReceive(DateTime value) {
      this.timestampReceive = value;
   }

   public DateTime getTimestampReply() {
      return this.timestampReply;
   }

   public void setTimestampReply(DateTime value) {
      this.timestampReply = value;
   }
}
