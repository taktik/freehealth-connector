package be.apb.gfddpp.common.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "tipSystemAudit",
   propOrder = {"success", "parameters", "messageId", "serviceName", "actorId", "created", "cbe"}
)
public class TipSystemAudit {
   protected boolean success;
   @XmlElement(
      required = true
   )
   protected String parameters;
   @XmlElement(
      required = true
   )
   protected String messageId;
   @XmlElement(
      required = true
   )
   protected String serviceName;
   @XmlElement(
      required = true
   )
   protected String actorId;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar created;
   @XmlElement(
      required = true
   )
   protected String cbe;

   public boolean isSuccess() {
      return this.success;
   }

   public void setSuccess(boolean value) {
      this.success = value;
   }

   public String getParameters() {
      return this.parameters;
   }

   public void setParameters(String value) {
      this.parameters = value;
   }

   public String getMessageId() {
      return this.messageId;
   }

   public void setMessageId(String value) {
      this.messageId = value;
   }

   public String getServiceName() {
      return this.serviceName;
   }

   public void setServiceName(String value) {
      this.serviceName = value;
   }

   public String getActorId() {
      return this.actorId;
   }

   public void setActorId(String value) {
      this.actorId = value;
   }

   public XMLGregorianCalendar getCreated() {
      return this.created;
   }

   public void setCreated(XMLGregorianCalendar value) {
      this.created = value;
   }

   public String getCbe() {
      return this.cbe;
   }

   public void setCbe(String value) {
      this.cbe = value;
   }
}
