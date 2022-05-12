package be.fgov.ehealth.mycarenet.attest.protocol.v2;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "SendAttestationRequest",
   namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v2"
)
public class SendAttestationRequest extends SendRequestType {
   private static final long serialVersionUID = 1L;

   public SendAttestationRequest() {
   }
}
