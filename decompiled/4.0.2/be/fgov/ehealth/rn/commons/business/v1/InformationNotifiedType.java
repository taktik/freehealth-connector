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
   name = "InformationNotifiedType",
   propOrder = {"ticket", "timestampReply", "notifiedIdentification"}
)
public class InformationNotifiedType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ticket"
   )
   protected String ticket;
   @XmlElement(
      name = "TimestampReply",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampReply;
   @XmlElement(
      name = "NotifiedIdentification",
      required = true
   )
   protected OrganizationIdentificationType notifiedIdentification;

   public InformationNotifiedType() {
   }

   public String getTicket() {
      return this.ticket;
   }

   public void setTicket(String value) {
      this.ticket = value;
   }

   public DateTime getTimestampReply() {
      return this.timestampReply;
   }

   public void setTimestampReply(DateTime value) {
      this.timestampReply = value;
   }

   public OrganizationIdentificationType getNotifiedIdentification() {
      return this.notifiedIdentification;
   }

   public void setNotifiedIdentification(OrganizationIdentificationType value) {
      this.notifiedIdentification = value;
   }
}
