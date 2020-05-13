package be.fgov.ehealth.mycarenet.attest.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "SendAttestationResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v1"
)
public class SendAttestationResponse extends SendResponseType {
   private static final long serialVersionUID = 1L;
}
