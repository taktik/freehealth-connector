package be.fgov.ehealth.mycarenet.attest.protocol.v3;

import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "SendAttestationRequest",
   namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3"
)
public class SendAttestationRequest extends SendRequestType {
   private static final long serialVersionUID = 1L;

   public SendAttestationRequest() {
   }
}
