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
   name = "InformationSupplierType",
   propOrder = {"ticket", "timestampReceive", "timestampReply", "supplierIdentification"}
)
public class InformationSupplierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ticket"
   )
   protected String ticket;
   @XmlElement(
      name = "TimestampReceive",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampReceive;
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
      name = "SupplierIdentification",
      required = true
   )
   protected OrganizationIdentificationType supplierIdentification;

   public InformationSupplierType() {
   }

   public String getTicket() {
      return this.ticket;
   }

   public void setTicket(String value) {
      this.ticket = value;
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

   public OrganizationIdentificationType getSupplierIdentification() {
      return this.supplierIdentification;
   }

   public void setSupplierIdentification(OrganizationIdentificationType value) {
      this.supplierIdentification = value;
   }
}
