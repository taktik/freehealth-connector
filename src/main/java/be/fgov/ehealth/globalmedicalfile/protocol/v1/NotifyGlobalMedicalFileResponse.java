package be.fgov.ehealth.globalmedicalfile.protocol.v1;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.soap.SOAPMessage;

@XmlRootElement(
        name = "NotifyGlobalMedicalFileResponse",
        namespace = "urn:be:fgov:ehealth:globalmedicalfile:protocol:v1"
)
public class NotifyGlobalMedicalFileResponse extends SendResponseType implements SoapConversationLogger {
    private static final long serialVersionUID = 7474695658294730459L;

    private SOAPMessage soapRequest;
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
}
