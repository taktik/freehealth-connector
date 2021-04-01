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
   name = "InformationNotificationCBSSType",
   propOrder = {"ticketCBSS", "timestampSent"}
)
public class InformationNotificationCBSSType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TicketCBSS",
      required = true
   )
   protected String ticketCBSS;
   @XmlElement(
      name = "TimestampSent",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampSent;

   public String getTicketCBSS() {
      return this.ticketCBSS;
   }

   public void setTicketCBSS(String value) {
      this.ticketCBSS = value;
   }

   public DateTime getTimestampSent() {
      return this.timestampSent;
   }

   public void setTimestampSent(DateTime value) {
      this.timestampSent = value;
   }
}
