package be.fgov.ehealth.messageservices.core.v1;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.*;
import javax.xml.soap.SOAPMessage;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RetrieveTransactionResponseType",
   propOrder = {"response", "acknowledge", "kmehrmessage"}
)
@XmlRootElement(
   name = "RetrieveTransactionResponse",
   namespace = "http://www.ehealth.fgov.be/messageservices/protocol/v1"
)
public class RetrieveTransactionResponse implements Serializable, SoapConversationLogger {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected ResponseType response;
   @XmlElement(
      required = true
   )
   protected AcknowledgeType acknowledge;
   protected Kmehrmessage kmehrmessage;
   @XmlAttribute(
      name = "messageProtocoleSchemaVersion"
   )
   protected BigDecimal messageProtocoleSchemaVersion;

   @XmlTransient
   private SOAPMessage soapRequest;
   @XmlTransient
   private SOAPMessage soapResponse;

   @Override
   public SOAPMessage getSoapRequest() {
      return this.soapRequest;
   }

   @Override
   public void setSoapRequest(SOAPMessage soapRequest) {
      this.soapRequest = soapRequest;
   }

   @Override
   public SOAPMessage getSoapResponse() {
      return this.soapResponse;
   }

   @Override
   public void setSoapResponse(SOAPMessage soapResponse) {
      this.soapResponse = soapResponse;
   }

   public ResponseType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseType value) {
      this.response = value;
   }

   public AcknowledgeType getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(AcknowledgeType value) {
      this.acknowledge = value;
   }

   public Kmehrmessage getKmehrmessage() {
      return this.kmehrmessage;
   }

   public void setKmehrmessage(Kmehrmessage value) {
      this.kmehrmessage = value;
   }

   public BigDecimal getMessageProtocoleSchemaVersion() {
      return this.messageProtocoleSchemaVersion;
   }

   public void setMessageProtocoleSchemaVersion(BigDecimal value) {
      this.messageProtocoleSchemaVersion = value;
   }
}
