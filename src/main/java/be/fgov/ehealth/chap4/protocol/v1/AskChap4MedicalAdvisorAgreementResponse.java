package be.fgov.ehealth.chap4.protocol.v1;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.soap.SOAPMessageContext;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AskChap4MedicalAdvisorAgreementResponseType"
)
@XmlRootElement(
   name = "AskChap4MedicalAdvisorAgreementResponse"
)
public class AskChap4MedicalAdvisorAgreementResponse extends AbstractChap4MedicalAdvisorAgreementResponseType implements Serializable, SoapConversationLogger {
   private static final long serialVersionUID = 1L;

   @XmlTransient private SOAPMessage soapRequest;
   @XmlTransient private SOAPMessage soapResponse;

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
}
