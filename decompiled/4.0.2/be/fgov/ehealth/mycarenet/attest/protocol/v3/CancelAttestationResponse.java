package be.fgov.ehealth.mycarenet.attest.protocol.v3;

import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "CancelAttestationResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3"
)
public class CancelAttestationResponse extends SendResponseType {
   private static final long serialVersionUID = 1L;

   public CancelAttestationResponse() {
   }
}
