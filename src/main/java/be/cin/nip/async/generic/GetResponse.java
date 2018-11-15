package be.cin.nip.async.generic;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.soap.SOAPMessage;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getResponse",
   propOrder = {"_return"}
)
@XmlRootElement(
   name = "getResponse"
)
public class GetResponse implements Serializable, SoapConversationLogger {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "return",
      required = true
   )
   protected Responses _return;

   public Responses getReturn() {
      return this._return;
   }

   public void setReturn(Responses value) {
      this._return = value;
   }

   @XmlTransient
   private SOAPMessage soapRequest;
   @XmlTransient private SOAPMessage soapResponse;

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
}
