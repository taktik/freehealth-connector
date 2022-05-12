package be.fgov.ehealth.mycarenet.attest.protocol.v3;

import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "SendAttestationResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3"
)
public class SendAttestationResponse extends SendResponseType {
   private static final long serialVersionUID = 1L;

   public SendAttestationResponse() {
   }
}
