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
   name = "InformationCustomerType",
   propOrder = {"ticket", "timestampSent", "customerIdentification"}
)
public class InformationCustomerType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ticket"
   )
   protected String ticket;
   @XmlElement(
      name = "TimestampSent",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampSent;
   @XmlElement(
      name = "CustomerIdentification",
      required = true
   )
   protected OrganizationIdentificationType customerIdentification;

   public InformationCustomerType() {
   }

   public String getTicket() {
      return this.ticket;
   }

   public void setTicket(String value) {
      this.ticket = value;
   }

   public DateTime getTimestampSent() {
      return this.timestampSent;
   }

   public void setTimestampSent(DateTime value) {
      this.timestampSent = value;
   }

   public OrganizationIdentificationType getCustomerIdentification() {
      return this.customerIdentification;
   }

   public void setCustomerIdentification(OrganizationIdentificationType value) {
      this.customerIdentification = value;
   }
}
