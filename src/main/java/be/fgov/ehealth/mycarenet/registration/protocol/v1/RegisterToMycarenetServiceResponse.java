package be.fgov.ehealth.mycarenet.registration.protocol.v1;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.SendResponseType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.soap.SOAPMessage;

@XmlRootElement(
   name = "RegisterToMycarenetServiceResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:registration:protocol:v1"
)
public class RegisterToMycarenetServiceResponse extends SendResponseType implements SoapConversationLogger {
   private static final long serialVersionUID = -6622884767890612976L;

   @XmlTransient private SOAPMessage soapRequest;
   @XmlTransient private SOAPMessage soapResponse;
   @XmlTransient private Integer upstreamTiming;

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

   @Override
   public Integer getUpstreamTiming() {
      return upstreamTiming;
   }

   @Override
   public void setUpstreamTiming(Integer upstreamTiming) {
      this.upstreamTiming = upstreamTiming;
   }
}
