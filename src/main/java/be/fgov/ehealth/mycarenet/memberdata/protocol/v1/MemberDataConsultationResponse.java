package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.soap.SOAPMessage;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendResponseMemberDataType"
)
@XmlRootElement(
   name = "MemberDataConsultationResponse"
)
public class MemberDataConsultationResponse extends SendResponseType implements Serializable, SoapConversationLogger {
   private static final long serialVersionUID = 1L;

   @XmlTransient
   private SOAPMessage soapRequest;
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
