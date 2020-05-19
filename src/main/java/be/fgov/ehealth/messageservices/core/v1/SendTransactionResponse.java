package be.fgov.ehealth.messageservices.core.v1;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.*;
import javax.xml.soap.SOAPMessage;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendTransactionResponseType",
   propOrder = {"response", "acknowledge", "kmehrmessage"}
)
@XmlRootElement(
   name = "SendTransactionResponse",
   namespace = "http://www.ehealth.fgov.be/messageservices/protocol/v1"
)
public class SendTransactionResponse implements Serializable, SoapConversationLogger {
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

   @XmlTransient private SOAPMessage soapRequest;
   @XmlTransient private SOAPMessage soapResponse;
   @XmlTransient private Integer upstreamTiming;

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

   @Override
   public SOAPMessage getSoapRequest() {
      return soapRequest;
   }

   @Override
   public void setSoapRequest(SOAPMessage soapRequest) {
      this.soapRequest = soapRequest;
   }

   @Override
   public SOAPMessage getSoapResponse() {
      return soapResponse;
   }

   @Override
   public void setSoapResponse(SOAPMessage soapResponse) {
      this.soapResponse = soapResponse;
   }

   @Override
   public Integer getUpstreamTiming() {
      return upstreamTiming;
   }

   @Override
   public void setUpstreamTiming(Integer upstreamTiming) {
      this.upstreamTiming = upstreamTiming;
   }
}
