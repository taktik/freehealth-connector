package be.fgov.ehealth.agreement.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "AskAgreementResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1"
)
public class AskAgreementResponse extends SendResponseType {
   private static final long serialVersionUID = 1L;

   public AskAgreementResponse() {
   }
}
