package be.fgov.ehealth.mycarenet.commons.protocol.v2;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.soap.SOAPMessageContext;

@XmlRootElement(
   name = "TarificationConsultationResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1"
)
public class TarificationConsultationResponse extends SendResponseType implements SoapConversationLogger {
   private static final long serialVersionUID = -1823503051478209431L;

   @XmlTransient private SOAPMessage soapRequest;
   @XmlTransient private SOAPMessage soapResponse;
   @XmlTransient private Integer upstreamTiming;

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
